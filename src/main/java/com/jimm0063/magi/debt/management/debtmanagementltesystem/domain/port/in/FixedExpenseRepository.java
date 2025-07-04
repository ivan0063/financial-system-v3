package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;

import java.util.List;

public interface FixedExpenseRepository {
    List<FixedExpense> findAllFixedExpenseBySystemUserAndActiveTrue(SystemUser systemUser);
}
