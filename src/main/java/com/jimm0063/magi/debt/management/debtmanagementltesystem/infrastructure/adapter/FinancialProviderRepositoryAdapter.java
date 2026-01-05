package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtSysUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderCatalogEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtSysUserMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtSysUserJpaRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FinancialProviderCatalogJpaRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FinancialProviderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialProviderRepositoryAdapter implements FinancialProviderRepository {
    private final FinancialProviderJpaRepository financialProviderJpaRepository;
    private final DebtSysUserMapper debtSysUserMapper;
    private final FinancialProviderMapper financialProviderMapper;
    private final DebtSysUserJpaRepository debtSysUserJpaRepositor;
    private final FinancialProviderCatalogJpaRepository financialProviderCatalogJpaRepository;

    public FinancialProviderRepositoryAdapter(FinancialProviderJpaRepository financialProviderJpaRepository,
                                              DebtSysUserMapper debtSysUserMapper, FinancialProviderMapper financialProviderMapper,
                                              DebtSysUserJpaRepository debtSysUserJpaRepositor, FinancialProviderCatalogJpaRepository financialProviderCatalogJpaRepository) {
        this.financialProviderJpaRepository = financialProviderJpaRepository;
        this.debtSysUserMapper = debtSysUserMapper;
        this.financialProviderMapper = financialProviderMapper;
        this.debtSysUserJpaRepositor = debtSysUserJpaRepositor;
        this.financialProviderCatalogJpaRepository = financialProviderCatalogJpaRepository;
    }

    @Override
    public List<FinancialProvider> findAllBySystemUser(DebtSysUser systemUser) {
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
    public FinancialProvider update(FinancialProvider financialProvider) {
        FinancialProviderEntity financialProviderEntity = financialProviderMapper.toEntity(financialProvider);
        return this.financialProviderMapper.toModel(financialProviderJpaRepository.save(financialProviderEntity));
    }

    @Override
    public FinancialProvider save(FinancialProvider financialProvider, String email, Integer financialProviderCatalogId) {
        DebtSysUserEntity user = debtSysUserJpaRepositor.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new EntityNotFoundException("User email " + email + " not found"));

        FinancialProviderCatalogEntity financialProviderCatalogEntity = financialProviderCatalogJpaRepository.findById(financialProviderCatalogId)
                .orElseThrow(() -> new  EntityNotFoundException("Financial provider catalog " + financialProviderCatalogId + " not found"));

        FinancialProviderEntity financialProviderEntity = financialProviderMapper.toEntity(financialProvider);
        financialProviderEntity.setDebtSysUser(user);
        financialProviderEntity.setFinancialProviderCatalog(financialProviderCatalogEntity);
        financialProviderEntity.setActive(true);

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
