package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;

import java.io.IOException;

public interface DoPayment {
    Payment cardPayment(String debtAccountCode) throws IOException;
}
