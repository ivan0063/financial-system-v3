package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.UserStatusDashboard;

public interface GetFinancialStatus {
    UserStatusDashboard getUserStatus(String email);
}
