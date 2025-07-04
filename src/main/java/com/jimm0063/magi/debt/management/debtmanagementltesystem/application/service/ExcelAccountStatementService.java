package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.AccountStatementDataExtractionUseCase;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service("MANUAL")
public class ExcelAccountStatementService implements AccountStatementDataExtractionUseCase {
    @Override
    public List<Debt> extractDebts(MultipartFile accountStatement, DebtAccount debtAccount) {
        List<Debt> debts = new ArrayList<>();

        try (InputStream inputStream = accountStatement.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            boolean isFirstRow = true; // Skip header row

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                // Read values from the Excel sheet
                String operationDate = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String name = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int monthsFinanced = (int) row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                int monthsPaid = (int) row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                double monthAmount = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String type = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                if (name.isEmpty()) break;

                // Calculate values
                double initialDebtAmount = monthAmount * monthsFinanced;

                // Create Debt object
                debts.add(
                    new Debt(
                        null,
                        name,
                        operationDate,
                        monthsPaid,
                        monthsFinanced,
                        initialDebtAmount,
                        monthAmount,
                        null,
                        null,
                        true,
                        debtAccount
                    )
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return debts;
    }
}
