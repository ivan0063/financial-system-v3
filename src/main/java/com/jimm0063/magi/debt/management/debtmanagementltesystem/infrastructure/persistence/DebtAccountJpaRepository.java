package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "debtAccount", path = "debtAccount")
public interface DebtAccountJpaRepository extends JpaRepository<DebtAccountEntity, String> {
    Optional<DebtAccountEntity> findDebtAccountByCode(String code);
}
