package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

public interface DebtDuplicationPreventUseCase {
    String getHashSum(Debt debt, String debtAccountCode);
}
