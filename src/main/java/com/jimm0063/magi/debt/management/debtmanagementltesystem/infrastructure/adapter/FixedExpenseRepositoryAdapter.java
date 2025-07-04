package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtSysUserMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FixedExpenseMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FixedExpenseJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FixedExpenseRepositoryAdapter implements FixedExpenseRepository {
    private final FixedExpenseJpaRepository fixedExpenseJpaRepository;
    private final FixedExpenseMapper fixedExpenseMapper;
    private final DebtSysUserMapper debtSysUserMapper;

    public FixedExpenseRepositoryAdapter(FixedExpenseJpaRepository fixedExpenseJpaRepository,
                                         FixedExpenseMapper fixedExpenseMapper, DebtSysUserMapper debtSysUserMapper) {
        this.fixedExpenseJpaRepository = fixedExpenseJpaRepository;
        this.fixedExpenseMapper = fixedExpenseMapper;
        this.debtSysUserMapper = debtSysUserMapper;
    }

    @Override
    public List<FixedExpense> findAllFixedExpenseBySystemUserAndActiveTrue(SystemUser systemUser) {
        DebtSysUserEntity debtSysUserEntity = debtSysUserMapper.toEntity(systemUser);
        return fixedExpenseJpaRepository.findAllByDebtSysUserAndActiveTrue(debtSysUserEntity)
                .stream()
                .map(fixedExpenseMapper::toModel)
                .toList();
    }
}
