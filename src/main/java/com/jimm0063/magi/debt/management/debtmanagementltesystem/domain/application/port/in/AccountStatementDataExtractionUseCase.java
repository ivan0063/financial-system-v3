package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface AccountStatementDataExtractionUseCase {
    List<Debt> extractDebts(MultipartFile accountStatement, DebtAccount debtAccount);
}
