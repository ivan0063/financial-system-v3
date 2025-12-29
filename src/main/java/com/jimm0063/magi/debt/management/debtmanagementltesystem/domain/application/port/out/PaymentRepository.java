package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;

import java.util.List;

public interface PaymentRepository {
    Payment save(Payment payment);

    List<Payment> findByDebtAccountCode(String debtAccountCode);

    Payment findLatest(String debtAccountCode);
}
