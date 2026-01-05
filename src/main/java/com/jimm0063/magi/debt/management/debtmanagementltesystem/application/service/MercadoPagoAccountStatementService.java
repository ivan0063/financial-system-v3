package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.AccountStatementDataExtractionUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.DebtTypeEnum;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("MERCADO_PAGO")
public class MercadoPagoAccountStatementService implements AccountStatementDataExtractionUseCase {

    /**
     * You’ll tune these markers based on the statement.
     * Keep multiple options because issuers love to rename sections.
     */
    private static final List<String> MSI_SECTION_START_MARKERS = List.of(
            "MENSUALIDADES SIN INTERESES",
            "MESES SIN INTERESES",
            "RESUMEN DE MENSUALIDADES",
            "COMPRAS A MENSUALIDADES"
    );

    private static final List<String> SECTION_END_MARKERS = List.of(
            "TOTAL",
            "RESUMEN",
            "DETALLE DE MOVIMIENTOS",
            "MOVIMIENTOS DEL PERIODO",
            "PAGOS",
            "COMISIONES",
            "INTERESES"
    );

    /**
     * Common row patterns (examples):
     * - "12/10/2025 AMAZON MX 1 de 12 $ 123.45 $ 1,481.40"
     * - "AMAZON MX 1/12 123.45"
     *
     * This regex tries to capture:
     * date (optional), description, installment (n), total months (m), monthly payment, original amount (optional)
     */
    private static final Pattern MSI_ROW_PATTERN = Pattern.compile(
            "(?:(\\d{2}[/-]\\d{2}[/-]\\d{4})\\s+)?"
                    + "(.+?)\\s+"
                    + "(\\d{1,2})\\s*(?:de|/|\\sde\\s)\\s*(\\d{1,2})\\s+"
                    + "\\$?\\s*([\\d.,]+)"
                    + "(?:\\s+\\$?\\s*([\\d.,]+))?",
            Pattern.CASE_INSENSITIVE
    );

    // If MercadoPago uses ISO dates in the MSI table, this helps too.
    private static final Pattern ISO_DATE = Pattern.compile("\\b(\\d{4}-\\d{2}-\\d{2})\\b");

    @Override
    public List<Debt> extractDebts(MultipartFile accountStatement, DebtAccount debtAccount) {
        Objects.requireNonNull(accountStatement, "accountStatement must not be null");
        Objects.requireNonNull(debtAccount, "debtAccount must not be null");

        try (InputStream is = accountStatement.getInputStream();
             PDDocument doc = Loader.loadPDF(is.readAllBytes())) {

            String fullText = extractAllText(doc);

            String msiSection = findMsiSection(fullText);
            if (msiSection.isBlank()) {
                return List.of();
            }

            LocalDate inferredOperationDate = inferStatementCutoffOrAnyDate(fullText);

            List<Debt> debts = parseMsiDebts(msiSection, debtAccount, inferredOperationDate);

            // Optional: filter obvious junk lines
            debts.removeIf(d -> d.getDescription() == null || d.getDescription().isBlank());

            return debts;

        } catch (Exception e) {
            throw new IllegalStateException("Failed to extract MercadoPago debts from PDF", e);
        }
    }

    private String extractAllText(PDDocument doc) throws Exception {
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);
        return stripper.getText(doc);
    }

    private String findMsiSection(String fullText) {
        String normalized = normalize(fullText);

        int start = indexOfAny(normalized, MSI_SECTION_START_MARKERS);
        if (start < 0) return "";

        int end = indexOfAnyAfter(normalized, SECTION_END_MARKERS, start + 1);
        if (end < 0) end = normalized.length();

        return normalized.substring(start, end).trim();
    }

    private List<Debt> parseMsiDebts(String sectionText, DebtAccount debtAccount, LocalDate fallbackOpDate) {
        List<Debt> out = new ArrayList<>();

        // Work line-by-line, but keep the whole section available.
        String[] lines = sectionText.split("\\R");
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (line.isBlank()) continue;

            Matcher m = MSI_ROW_PATTERN.matcher(line);
            if (!m.find()) continue;

            String dateStr = safe(m.group(1));
            String desc = cleanDescription(safe(m.group(2)));
            int current = parseIntSafe(m.group(3));
            int total = parseIntSafe(m.group(4));
            BigDecimal monthlyPayment = parseMoneySafe(m.group(5));
            BigDecimal originalAmount = parseMoneySafe(m.group(6)); // may be null

            String operationDate = resolveOperationDate(dateStr, fallbackOpDate);

            Debt d = new Debt();
            d.setActive(true);
            d.setDescription(desc);
            d.setOperationDate(operationDate); // v1: String
            d.setMaxFinancingTerm(total);
            d.setCurrentInstallment(current);
            d.setMonthlyPayment(monthlyPayment != null ? monthlyPayment : BigDecimal.ZERO);
            d.setOriginalAmount(originalAmount != null ? originalAmount : BigDecimal.ZERO);
            d.setDebtType(DebtTypeEnum.CARD);

            // In v1 you store a code string, keep it aligned with your model:
            d.setDebtAccount(debtAccount);

            out.add(d);
        }

        return out;
    }

    private String resolveOperationDate(String dateStr, LocalDate fallback) {
        if (dateStr != null && !dateStr.isBlank()) {
            // dd/MM/yyyy or dd-MM-yyyy
            DateTimeFormatter fmt = dateStr.contains("-")
                    ? DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    : DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate d = LocalDate.parse(dateStr, fmt);
                return d.toString(); // ISO string
            } catch (Exception ignored) {}
        }
        return fallback != null ? fallback.toString() : LocalDate.now().toString();
    }

    private LocalDate inferStatementCutoffOrAnyDate(String fullText) {
        // This is intentionally naive: find the first ISO date or any dd/MM/yyyy in the full text
        String normalized = normalize(fullText);

        Matcher iso = ISO_DATE.matcher(normalized);
        if (iso.find()) {
            try { return LocalDate.parse(iso.group(1)); } catch (Exception ignored) {}
        }

        Pattern dmy = Pattern.compile("\\b(\\d{2}[/-]\\d{2}[/-]\\d{4})\\b");
        Matcher m = dmy.matcher(normalized);
        if (m.find()) {
            String s = m.group(1);
            DateTimeFormatter fmt = s.contains("-")
                    ? DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    : DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try { return LocalDate.parse(s, fmt); } catch (Exception ignored) {}
        }
        return null;
    }

    private String normalize(String s) {
        // Keep it simple; you can add accent folding later if needed.
        return s == null ? "" : s.replace('\u00A0', ' ').trim();
    }

    private int indexOfAny(String haystack, List<String> needles) {
        int best = -1;
        for (String n : needles) {
            int i = haystack.toUpperCase(Locale.ROOT).indexOf(n.toUpperCase(Locale.ROOT));
            if (i >= 0 && (best < 0 || i < best)) best = i;
        }
        return best;
    }

    private int indexOfAnyAfter(String haystack, List<String> needles, int afterIndex) {
        String sub = haystack.substring(Math.max(0, afterIndex));
        int best = -1;
        for (String n : needles) {
            int i = sub.toUpperCase(Locale.ROOT).indexOf(n.toUpperCase(Locale.ROOT));
            if (i >= 0 && (best < 0 || i < best)) best = i;
        }
        return best < 0 ? -1 : (afterIndex + best);
    }

    private String cleanDescription(String desc) {
        // Remove double spaces and obvious column separators
        return desc.replaceAll("\\s{2,}", " ").replace("|", "").trim();
    }

    private String safe(String s) { return s == null ? "" : s.trim(); }

    private int parseIntSafe(String s) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return 0; }
    }

    private BigDecimal parseMoneySafe(String s) {
        if (s == null) return null;
        String cleaned = s.trim()
                .replace("$", "")
                .replace(" ", "")
                .replace(",", "");
        if (cleaned.isBlank()) return null;
        try { return new BigDecimal(cleaned); } catch (Exception e) { return null; }
    }
}