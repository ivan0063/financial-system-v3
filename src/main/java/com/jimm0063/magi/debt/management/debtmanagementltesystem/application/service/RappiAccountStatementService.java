package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.AccountStatementDataExtractionUseCase;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
        import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("RAPPI")
public class RappiAccountStatementService implements AccountStatementDataExtractionUseCase {

    // "Fecha de corte 06 nov. 2025"
    private static final Pattern CUTOFF_PATTERN = Pattern.compile(
            "Fecha\\s+de\\s+corte\\s+(?<dd>\\d{1,2})\\s+(?<mon>[a-z]{3})\\.\\s+(?<yyyy>\\d{4})",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern BLOCK_START = Pattern.compile("^Compras\\s+a\\s+meses\\s*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern BLOCK_END = Pattern.compile("^Subtotal\\s+\\$.*$", Pattern.CASE_INSENSITIVE);

    private static final Pattern BLOCK_HEADER = Pattern.compile(
            "^Fecha\\s+Más\\s+detalle\\s+Monto\\s+original\\s+Pendiente\\s+Interés\\s+#\\s+de\\s+Mensualidad\\s+Mensualidad\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    // 2025-07-18 AMAZON MX A MESES M $ 602.34 $ 401.54 $ 0.00 4 de 12 $ 50.20
    private static final Pattern MSI_ROW = Pattern.compile(
            "^(?<date>\\d{4}-\\d{2}-\\d{2})\\s+" +
                    "(?<detail>.+?)\\s+" +
                    "\\$\\s*(?<orig>[\\d,]+\\.\\d{2})\\s+" +
                    "\\$\\s*(?<pending>[\\d,]+\\.\\d{2})\\s+" +
                    "\\$\\s*(?<interest>[\\d,]+\\.\\d{2})\\s+" +
                    "(?<n>\\d+)\\s+de\\s+(?<m>\\d+)\\s+" +
                    "\\$\\s*(?<monthly>[\\d,]+\\.\\d{2})\\s*$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public List<Debt> extractDebts(MultipartFile accountStatement, DebtAccount debtAccount) {
        try {
            String text = extractText(accountStatement);

            // optional: if you want to store statement cycle later, you can return/emit this date
            Optional<LocalDate> cutoff = extractCutoffDate(text);

            List<String> blockLines = extractComprasAMesesBlock(text);
            List<MsiRow> rows = parseMsiRows(blockLines);

            List<Debt> debts = new ArrayList<>(rows.size());
            for (MsiRow r : rows) {
                debts.add(mapToDebt(r, debtAccount));
            }

            return debts;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract debts from RAPPI statement PDF", e);
        }
    }

    private Debt mapToDebt(MsiRow r, DebtAccount debtAccount) {
        Debt d = new Debt();
        d.setActive(true);

        // Your v1 schema uses operation_date as varchar(255), so store ISO date.
        d.setOperationDate(r.operationDate().toString());

        d.setDescription(r.detail());
        d.setMaxFinancingTerm(r.totalInstallments());
        d.setCurrentInstallment(r.currentInstallment());

        // If your entity is double precision:
        d.setOriginalAmount(toDouble2(r.originalAmount()));
        d.setMonthlyPayment(toDouble2(r.monthlyPayment()));

        // relation to account (depending on your mapping)
        // If Debt has String debtAccount:
        d.setDebtAccount(debtAccount);

        // If you also keep audit timestamps in entity and your JPA fills them, ignore.
        return d;
    }

    private static double toDouble2(BigDecimal v) {
        if (v == null) return 0d;
        return v.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private String extractText(MultipartFile accountStatementRappi) throws Exception {
        try (PDDocument doc = Loader.loadPDF(accountStatementRappi.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);
            return stripper.getText(doc);
        }
    }

    private Optional<LocalDate> extractCutoffDate(String text) {
        Matcher m = CUTOFF_PATTERN.matcher(text);
        if (!m.find()) return Optional.empty();
        int dd = Integer.parseInt(m.group("dd"));
        int yyyy = Integer.parseInt(m.group("yyyy"));
        int mon = mxMonthToNumber(m.group("mon"));
        return Optional.of(LocalDate.of(yyyy, mon, dd));
    }

    private List<String> extractComprasAMesesBlock(String text) {
        List<String> lines = Arrays.asList(text.split("\\R"));
        List<String> out = new ArrayList<>();

        boolean inBlock = false;

        for (String raw : lines) {
            String line = normalizeSpaces(raw);

            if (!inBlock) {
                if (BLOCK_START.matcher(line).matches()) {
                    inBlock = true;
                }
                continue;
            }

            if (line.isBlank()) continue;
            if (BLOCK_END.matcher(line).matches()) break;
            if (BLOCK_HEADER.matcher(line).matches()) continue;

            out.add(line);
        }
        return out;
    }

    private List<MsiRow> parseMsiRows(List<String> lines) {
        List<MsiRow> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();

        for (String line : lines) {
            if (buffer.length() > 0) buffer.append(' ');
            buffer.append(line);

            String candidate = buffer.toString().trim();
            Matcher m = MSI_ROW.matcher(candidate);
            if (m.matches()) {
                result.add(toRow(m));
                buffer.setLength(0);
            } else if (candidate.length() > 900) {
                buffer.setLength(0);
            }
        }

        return result;
    }

    private MsiRow toRow(Matcher m) {
        LocalDate opDate = LocalDate.parse(m.group("date"), DateTimeFormatter.ISO_LOCAL_DATE);
        String detail = normalizeSpaces(m.group("detail"));
        BigDecimal original = money(m.group("orig"));
        BigDecimal pending = money(m.group("pending"));
        BigDecimal monthly = money(m.group("monthly"));
        int n = Integer.parseInt(m.group("n"));
        int total = Integer.parseInt(m.group("m"));

        return new MsiRow(opDate, detail, original, pending, monthly, n, total);
    }

    private static BigDecimal money(String raw) {
        return new BigDecimal(raw.replace(",", "").trim()).setScale(2, RoundingMode.HALF_UP);
    }

    private static String normalizeSpaces(String s) {
        return s == null ? "" : s.replaceAll("\\s+", " ").trim();
    }

    private static int mxMonthToNumber(String mon) {
        String m = mon.toLowerCase(Locale.ROOT).replace(".", "").trim();
        return switch (m) {
            case "ene" -> 1;
            case "feb" -> 2;
            case "mar" -> 3;
            case "abr" -> 4;
            case "may" -> 5;
            case "jun" -> 6;
            case "jul" -> 7;
            case "ago" -> 8;
            case "sep" -> 9;
            case "oct" -> 10;
            case "nov" -> 11;
            case "dic" -> 12;
            default -> throw new IllegalArgumentException("Unknown MX month: " + mon);
        };
    }

    private record MsiRow(
            LocalDate operationDate,
            String detail,
            BigDecimal originalAmount,
            BigDecimal pendingAmount,
            BigDecimal monthlyPayment,
            int currentInstallment,
            int totalInstallments
    ) {}
}