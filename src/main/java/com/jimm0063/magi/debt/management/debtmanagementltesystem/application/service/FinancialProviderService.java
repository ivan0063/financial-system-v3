package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFinancialProviderCatalogsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFinancialProviderUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialProviderService implements FindAllFinancialProviderUseCase, FindAllFinancialProviderCatalogsUseCase {
    private final FinancialProviderRepository financialProviderRepository;
    private final FinancialProviderCatalogRepository financialProviderCatalogRepository;

    public FinancialProviderService(FinancialProviderRepository financialProviderRepository, FinancialProviderCatalogRepository financialProviderCatalogRepository) {
        this.financialProviderRepository = financialProviderRepository;
        this.financialProviderCatalogRepository = financialProviderCatalogRepository;
    }

    @Override
    public List<FinancialProvider> getActiveByEmail(String userEmail) {
        return this.financialProviderRepository.findAllByEmail(userEmail);
    }

    @Override
    public List<FinancialProviderCatalog> getCatalogList() {
        return financialProviderCatalogRepository.findAllFinancialProviderCatalogs();
    }
}
