package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;

import java.util.List;

public interface FindAllFixedExpenseCatalogsUseCase {
    List<FixedExpenseCatalog> getCatalogList();
}
