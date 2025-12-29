package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;

import java.io.IOException;

public interface DoPayment {
    Payment cardPayment(String debtAccountCode) throws IOException;
}
