package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//@RepositoryRestResource(collectionResourceRel = "financialProviderCatalog", path = "financialProviderCatalog")
@Repository
public interface FinancialProviderCatalogJpaRepository extends JpaRepository<FinancialProviderCatalogEntity, Integer> {
    List<FinancialProviderCatalogEntity> findAll();
}
