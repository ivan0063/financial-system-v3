package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;

import java.util.List;

public interface LoadDebtList {
    List<Debt> saveUnrepeated(List<Debt> debts, String debtAccountCode);
}
