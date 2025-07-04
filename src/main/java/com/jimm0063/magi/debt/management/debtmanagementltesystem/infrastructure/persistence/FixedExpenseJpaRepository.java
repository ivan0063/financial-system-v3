package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "fixedExpense", path = "fixedExpense")
public interface FixedExpenseJpaRepository extends JpaRepository<FixedExpenseEntity, Integer> {
    List<FixedExpenseEntity> findAllByDebtSysUserAndActiveTrue(DebtSysUserEntity user);
}
