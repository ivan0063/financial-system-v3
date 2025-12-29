package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.PaymentRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.PaymentEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.PaymentMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.PaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    public PaymentRepositoryAdapter(PaymentJpaRepository paymentJpaRepository, PaymentMapper paymentMapper) {
        this.paymentJpaRepository = paymentJpaRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = paymentMapper.toEntity(payment);
        return paymentMapper.toModel(this.paymentJpaRepository.save(paymentEntity));
    }

    @Override
    public List<Payment> findByDebtAccountCode(String debtAccountCode) {
        return paymentJpaRepository.findByDebtAccountCode(debtAccountCode)
                .stream()
                .map(paymentMapper::toModel)
                .toList();
    }

    @Override
    public Payment findLatest(String debtAccountCode) {
        return paymentJpaRepository.findTopByDebtAccount_CodeOrderByCreatedAtDesc(debtAccountCode)
                .map(paymentMapper::toModel)
                .orElseThrow(()-> new EntityNotFoundException("There is no payments associated to " + debtAccountCode + " debt account"));
    }
}
