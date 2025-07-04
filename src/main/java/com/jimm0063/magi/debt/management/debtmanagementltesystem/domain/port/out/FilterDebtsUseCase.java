package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

import java.util.List;

public interface FilterDebtsUseCase {
    List<Debt> filterAccountStatementDebts(List<Debt> accountStatementDebts, String debtAccountCode);
}
