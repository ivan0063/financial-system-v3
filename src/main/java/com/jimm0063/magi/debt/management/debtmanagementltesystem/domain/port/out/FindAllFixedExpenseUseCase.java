package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;

import java.util.List;

public interface FindAllFixedExpenseUseCase {
    List<FixedExpense> getByEmail(String userEmail);
}
