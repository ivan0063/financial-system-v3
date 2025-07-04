package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;

import java.util.List;

public interface DebtRepository {
    List<Debt> findAllDebtsByDebtAccountAndActiveTrue(String debtAccountCode);

    List<Debt> saveAll(List<Debt> debt, DebtAccount debtAccount);

    List<Debt> saveAll(List<Debt> debt);
}
