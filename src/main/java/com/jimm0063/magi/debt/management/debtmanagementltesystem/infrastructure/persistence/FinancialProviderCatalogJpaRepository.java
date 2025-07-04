package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "financialProviderCatalog", path = "financialProviderCatalog")
public interface FinancialProviderCatalogJpaRepository extends JpaRepository<FinancialProviderCatalogEntity, Integer> {
}
