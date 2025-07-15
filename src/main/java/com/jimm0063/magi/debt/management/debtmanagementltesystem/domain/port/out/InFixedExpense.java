package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;

public interface InFixedExpense {
    FixedExpense createUpdateFixedExpense(FixedExpense fixedExpense);
}
