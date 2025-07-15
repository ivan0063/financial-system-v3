package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtAccountEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "debtAccount", path = "debtAccount")
@Repository
public interface DebtAccountJpaRepository extends JpaRepository<DebtAccountEntity, String> {
    Optional<DebtAccountEntity> findDebtAccountByCodeAndActiveTrue(String code);

    List<DebtAccountEntity> findAllByFinancialProvider(FinancialProviderEntity financialProvider);
}
