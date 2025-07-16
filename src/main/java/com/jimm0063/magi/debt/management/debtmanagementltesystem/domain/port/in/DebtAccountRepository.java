package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;

import java.util.List;
import java.util.Optional;

public interface DebtAccountRepository {
    Optional<DebtAccount> findDebtAccountByCodeAndActiveTrue(String code);
    List<DebtAccount> findAllByFinancialProvider(FinancialProvider financialProvider);
    List<DebtAccount> findAllByFinancialProviderCodeAndActiveTrue(String financialProviderCode);
    DebtAccount save(DebtAccount debtAccount);
    void delete(String debtAccountCode);
}
