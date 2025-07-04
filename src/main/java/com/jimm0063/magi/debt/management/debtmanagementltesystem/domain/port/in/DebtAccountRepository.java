package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface DebtAccountRepository {
    Optional<DebtAccount> findDebtAccountByCodeAndActiveTrue(String code);
    List<DebtAccount> findAllByFinancialProvider(FinancialProvider financialProvider);
}
