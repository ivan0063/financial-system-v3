package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "financialProvider", path = "financialProvider")
@Repository
public interface FinancialProviderJpaRepository extends JpaRepository<FinancialProviderEntity, String> {
    List<FinancialProviderEntity> findAllByDebtSysUser(DebtSysUserEntity debtSysUser);
    List<FinancialProviderEntity> findAllByDebtSysUser_Email(String email);
}
