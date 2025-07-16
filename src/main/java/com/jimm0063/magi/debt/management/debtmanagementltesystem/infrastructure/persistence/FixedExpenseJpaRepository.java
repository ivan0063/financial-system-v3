package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource(collectionResourceRel = "fixedExpense", path = "fixedExpense")
@Repository
public interface FixedExpenseJpaRepository extends JpaRepository<FixedExpenseEntity, Integer> {
    List<FixedExpenseEntity> findAllByDebtSysUserAndActiveTrue(DebtSysUserEntity user);
    Optional<FixedExpenseEntity> findByIdAndActiveTrue(Integer id);
}
