package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtSysUserMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FinancialProviderJpaRepository;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialProviderRepositoryAdapter implements FinancialProviderRepository {
    private final FinancialProviderJpaRepository financialProviderJpaRepository;
    private final DebtSysUserMapper debtSysUserMapper;
    private final FinancialProviderMapper financialProviderMapper;
    private final JpaContext jpaContext;

    public FinancialProviderRepositoryAdapter(FinancialProviderJpaRepository financialProviderJpaRepository,
                                              DebtSysUserMapper debtSysUserMapper,
                                              FinancialProviderMapper financialProviderMapper, JpaContext jpaContext) {
        this.financialProviderJpaRepository = financialProviderJpaRepository;
        this.debtSysUserMapper = debtSysUserMapper;
        this.financialProviderMapper = financialProviderMapper;
        this.jpaContext = jpaContext;
    }

    @Override
    public List<FinancialProvider> findAllBySystemUser(SystemUser systemUser) {
        DebtSysUserEntity debtSysUserEntity = debtSysUserMapper.toEntity(systemUser);
        return this.financialProviderJpaRepository.findAllByDebtSysUser(debtSysUserEntity)
                .stream()
                .map(financialProviderMapper::toModel)
                .toList();
    }

    @Override
    public List<FinancialProvider> findAllByEmail(String email) {
        return financialProviderJpaRepository.findAllByDebtSysUser_Email(email)
                .stream()
                .map(this.financialProviderMapper::toModel)
                .toList();
    }

    @Override
    public FinancialProvider save(FinancialProvider financialProvider) {
        FinancialProviderEntity financialProviderEntity = financialProviderMapper.toEntity(financialProvider);
        return this.financialProviderMapper.toModel(financialProviderJpaRepository.save(financialProviderEntity));
    }

    @Override
    public void delete(String financialProviderCode) {
        FinancialProviderEntity financialProviderEntity = financialProviderJpaRepository.findById(financialProviderCode)
                        .orElseThrow(() -> new IllegalArgumentException("Financial provider code " + financialProviderCode + " not found"));
        financialProviderEntity.setActive(false);
        this.financialProviderJpaRepository.save(financialProviderEntity);
    }
}
