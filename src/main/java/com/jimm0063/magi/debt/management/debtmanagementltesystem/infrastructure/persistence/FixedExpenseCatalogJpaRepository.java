package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseCatalogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "fixedExpenseCatalog", path = "fixedExpenseCatalog")
@Repository
public interface FixedExpenseCatalogJpaRepository extends JpaRepository<FixedExpenseCatalogEntity, Integer> {
}
