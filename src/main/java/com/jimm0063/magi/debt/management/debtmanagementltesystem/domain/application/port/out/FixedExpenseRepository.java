package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtSysUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.FixedExpenseReq;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseRepository {
    List<FixedExpense> findAllFixedExpenseBySystemUserAndActiveTrue(DebtSysUser systemUser);
    List<FixedExpense> findAllFixedExpenseByEmailAndActiveTrue(String email);
    Optional<FixedExpense> findByIdAndActiveTrue(Integer fixedExpenseId);
    FixedExpense save(FixedExpenseReq fixedExpense, Integer fixedExpenseCatalogId);
    FixedExpense update(FixedExpense fixedExpense);
    void delete(Integer fixedExpenseId);
}
