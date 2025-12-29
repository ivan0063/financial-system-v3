package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;

import java.util.List;

public interface FindAllFinancialProviderCatalogsUseCase {
    List<FinancialProviderCatalog> getCatalogList();
}
