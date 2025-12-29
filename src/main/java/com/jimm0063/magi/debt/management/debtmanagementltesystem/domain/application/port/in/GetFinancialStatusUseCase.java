package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.UserStatusDashboard;

public interface GetFinancialStatusUseCase {
    UserStatusDashboard getUserStatus(String email);
}
