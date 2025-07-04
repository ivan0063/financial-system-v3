package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.UserRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtSysUserMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtSysUserJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepository {
    private final DebtSysUserJpaRepository debtSysUserJpaRepository;
    private final DebtSysUserMapper debtSysUserMapper;

    public UserRepositoryAdapter(DebtSysUserJpaRepository debtSysUserJpaRepository, DebtSysUserMapper debtSysUserMapper) {
        this.debtSysUserJpaRepository = debtSysUserJpaRepository;
        this.debtSysUserMapper = debtSysUserMapper;
    }

    @Override
    public Optional<SystemUser> findUserByEmail(String email) {
        return this.debtSysUserJpaRepository.findByEmailAndActiveTrue(email)
                .map(debtSysUserMapper::toModel);
    }
}
