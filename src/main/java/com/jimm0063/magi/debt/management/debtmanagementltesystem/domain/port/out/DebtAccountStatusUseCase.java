package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.DebtAccountStatusDto;

public interface DebtAccountStatusUseCase {
    DebtAccountStatusDto getStatus(String debtAccountCode);
}
