package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtProjection;

import java.time.LocalDate;
import java.util.List;

public interface PaymentProjection {
    List getDebtProjection(LocalDate to, String email, List<DebtProjection> debtProjection);
}
