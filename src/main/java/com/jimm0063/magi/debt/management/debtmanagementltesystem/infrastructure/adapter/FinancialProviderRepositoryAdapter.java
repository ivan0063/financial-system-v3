package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtSysUserMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FinancialProviderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialProviderRepositoryAdapter implements FinancialProviderRepository {
    private final FinancialProviderJpaRepository financialProviderJpaRepository;
    private final DebtSysUserMapper debtSysUserMapper;
    private final FinancialProviderMapper financialProviderMapper;

    public FinancialProviderRepositoryAdapter(FinancialProviderJpaRepository financialProviderJpaRepository,
                                              DebtSysUserMapper debtSysUserMapper,
                                              FinancialProviderMapper financialProviderMapper) {
        this.financialProviderJpaRepository = financialProviderJpaRepository;
        this.debtSysUserMapper = debtSysUserMapper;
        this.financialProviderMapper = financialProviderMapper;
    }

    @Override
    public List<FinancialProvider> findAllBySystemUser(SystemUser systemUser) {
        DebtSysUserEntity debtSysUserEntity = debtSysUserMapper.toEntity(systemUser);
        return this.financialProviderJpaRepository.findAllByDebtSysUser(debtSysUserEntity)
                .stream()
                .map(financialProviderMapper::toModel)
                .toList();
    }
}
