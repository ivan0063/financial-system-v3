package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseCatalogRepository {
    List<FixedExpenseCatalog> findAll();
    Optional<FixedExpenseCatalog> findById(Integer id);
    FixedExpenseCatalog save(FixedExpenseCatalog fixedExpenseCatalog);
    void delete(Integer fixedExpenseCatalogId);
}
