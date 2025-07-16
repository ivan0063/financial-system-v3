package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;

import java.util.List;

public interface FindAllFinancialProviderUseCase {
    List<FinancialProvider> getActiveByEmail(String userEmail);
}
