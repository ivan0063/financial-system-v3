package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;

import java.util.List;

public interface FindAllFixedExpenseCatalogsUseCase {
    List<FixedExpenseCatalog> getCatalogList();
}
