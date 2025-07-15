package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFixedExpenseCatalog;
import org.springframework.stereotype.Service;

@Service
public class FixedExpenseService implements InFixedExpense, InFixedExpenseCatalog {
    private final FixedExpenseCatalogRepository fixedExpenseCatalogRepository;
    private final FixedExpenseRepository fixedExpenseRepository;

    public FixedExpenseService(FixedExpenseCatalogRepository fixedExpenseCatalogRepository, FixedExpenseRepository fixedExpenseRepository) {
        this.fixedExpenseCatalogRepository = fixedExpenseCatalogRepository;
        this.fixedExpenseRepository = fixedExpenseRepository;
    }

    @Override
    public FixedExpenseCatalog createUpdateFixedExpenseCatalog(FixedExpenseCatalog fixedExpenseCatalog) {
        return fixedExpenseCatalogRepository.save(fixedExpenseCatalog);
    }

    @Override
    public FixedExpense createUpdateFixedExpense(FixedExpense fixedExpense) {
        return fixedExpenseRepository.save(fixedExpense);
    }
}
