package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.FindAllFixedExpenseCatalogsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.FindAllFixedExpenseUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
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
