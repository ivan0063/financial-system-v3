package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;

import java.util.Optional;

public interface UserRepository {
    Optional<SystemUser> findUserByEmail(String email);
}
