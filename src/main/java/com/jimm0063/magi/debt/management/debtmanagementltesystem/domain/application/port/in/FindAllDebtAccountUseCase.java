package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;

import java.util.List;

public interface FindAllDebtAccountUseCase {
    List<DebtAccount> getActiveByFinancialProvider(String financialProviderCode);
}
