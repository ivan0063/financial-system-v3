package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;

import java.util.List;

public interface FinancialProviderRepository {
    List<FinancialProvider> findAllBySystemUser(SystemUser systemUser);
}
