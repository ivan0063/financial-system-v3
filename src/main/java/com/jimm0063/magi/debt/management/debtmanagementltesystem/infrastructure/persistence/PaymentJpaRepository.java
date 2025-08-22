package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Integer> {
    List<PaymentEntity> findByDebtAccountCode(String debtAccountCode);

    Optional<PaymentEntity> findTopByDebtAccount_CodeOrderByCreatedAtDesc(String debtAccountCode);
}
