package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtSysUser;

import java.util.List;

public interface FinancialProviderRepository {
    List<FinancialProvider> findAllBySystemUser(DebtSysUser systemUser);
    List<FinancialProvider> findAllByEmail(String email);
    FinancialProvider save(FinancialProvider financialProvider, String email);
    FinancialProvider update(FinancialProvider financialProvider);
    void delete(String financialProviderCode);
}
