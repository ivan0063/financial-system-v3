package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "fixedExpenseCatalog", path = "fixedExpenseCatalog")
public interface FixedExpenseCatalogJpaRepository extends JpaRepository<FixedExpenseCatalogEntity, Integer> {
}
