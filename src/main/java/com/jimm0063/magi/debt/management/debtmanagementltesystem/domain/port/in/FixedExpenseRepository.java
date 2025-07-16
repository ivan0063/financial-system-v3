package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseRepository {
    List<FixedExpense> findAllFixedExpenseBySystemUserAndActiveTrue(SystemUser systemUser);
    Optional<FixedExpense> findByIdAndActiveTrue(Integer fixedExpenseId);
    FixedExpense save(FixedExpense fixedExpense);
    void delete(Integer fixedExpenseId);
}
