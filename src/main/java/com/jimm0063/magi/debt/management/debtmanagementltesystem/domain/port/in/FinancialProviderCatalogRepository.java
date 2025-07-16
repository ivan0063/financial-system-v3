package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;

import java.util.List;

public interface FinancialProviderCatalogRepository {
    List<FinancialProviderCatalog> findAllFinancialProviderCatalogs();
    FinancialProviderCatalog save(FinancialProviderCatalog financialProviderCatalog);
    void delete(Integer financialProviderCatalogId);
}
