package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.DebtAccountStatusDto;

public interface DebtAccountStatusUseCase {
    DebtAccountStatusDto getStatus(String debtAccountCode);
}
