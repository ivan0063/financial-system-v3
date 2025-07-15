package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;

public interface InFinancialProvider {
    FinancialProvider createUpdateFinancialProvider(FinancialProvider financialProvider);
}
