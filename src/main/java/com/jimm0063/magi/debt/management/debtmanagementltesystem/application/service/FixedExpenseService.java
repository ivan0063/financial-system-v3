package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFixedExpenseCatalogsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFixedExpenseUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedExpenseService implements FindAllFixedExpenseUseCase, FindAllFixedExpenseCatalogsUseCase {
    private final FixedExpenseRepository fixedExpenseRepository;
    private final FixedExpenseCatalogRepository fixedExpenseCatalogRepository;

    public FixedExpenseService(FixedExpenseRepository fixedExpenseRepository, FixedExpenseCatalogRepository fixedExpenseCatalogRepository) {
        this.fixedExpenseRepository = fixedExpenseRepository;
        this.fixedExpenseCatalogRepository = fixedExpenseCatalogRepository;
    }

    @Override
    public List<FixedExpense> getByEmail(String userEmail) {
        return this.fixedExpenseRepository.findAllFixedExpenseByEmailAndActiveTrue(userEmail);
    }

    @Override
    public List<FixedExpenseCatalog> getCatalogList() {
        return fixedExpenseCatalogRepository.findAll();
    }
}
