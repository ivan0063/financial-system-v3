package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

import java.util.List;

public interface DebtRepository {
    List<Debt> findAllDebtsByDebtAccountAndActiveTrue(String debtAccountCode);

}
