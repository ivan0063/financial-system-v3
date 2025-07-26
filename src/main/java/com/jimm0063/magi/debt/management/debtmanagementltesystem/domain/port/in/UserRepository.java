package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtSysUser;

import java.util.Optional;

public interface UserRepository {
    Optional<DebtSysUser> findUserByEmail(String email);
}
