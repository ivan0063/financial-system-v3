package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "financialProvider", path = "financialProvider")
public interface FinancialProviderJpaRepository extends JpaRepository<FinancialProviderEntity, String> {
    List<FinancialProviderEntity> findAllByDebtSysUser(DebtSysUserEntity debtSysUser);
}
