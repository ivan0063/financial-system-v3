package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFinancialProviderCatalog;
import org.springframework.stereotype.Service;

@Service
public class FinancialProviderService implements InFinancialProviderCatalog, InFinancialProvider {
    private final FinancialProviderCatalogRepository financialProviderCatalogRepository;
    private final FinancialProviderRepository financialProviderRepository;

    public FinancialProviderService(FinancialProviderCatalogRepository financialProviderCatalogRepository, FinancialProviderRepository financialProviderRepository) {
        this.financialProviderCatalogRepository = financialProviderCatalogRepository;
        this.financialProviderRepository = financialProviderRepository;
    }

    @Override
    public FinancialProvider createUpdateFinancialProvider(FinancialProvider financialProvider) {
        return this.financialProviderRepository.save(financialProvider);
    }

    @Override
    public FinancialProviderCatalog createUpdateFinancialProviderCatalog(FinancialProviderCatalog financialProviderCatalog) {
        return this.financialProviderCatalogRepository.save(financialProviderCatalog);
    }
}
