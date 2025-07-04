package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtAccountMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtAccountJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DebtAccountRepositoryAdapter implements DebtAccountRepository {
    private final DebtAccountJpaRepository debtAccountJpaRepository;
    private final DebtAccountMapper debtAccountMapper;
    private final FinancialProviderMapper financialProviderMapper;

    public DebtAccountRepositoryAdapter(DebtAccountJpaRepository debtAccountJpaRepository,
                                        DebtAccountMapper debtAccountMapper,
                                        FinancialProviderMapper financialProviderMapper) {
        this.debtAccountJpaRepository = debtAccountJpaRepository;
        this.debtAccountMapper = debtAccountMapper;
        this.financialProviderMapper = financialProviderMapper;
    }

    @Override
    public Optional<DebtAccount> findDebtAccountByCodeAndActiveTrue(String code) {
        return this.debtAccountJpaRepository.findDebtAccountByCodeAndActiveTrue(code)
                .map(this.debtAccountMapper::toModel);
    }

    @Override
    public List<DebtAccount> findAllByFinancialProvider(FinancialProvider financialProvider) {
        FinancialProviderEntity financialProviderEntity = financialProviderMapper.toEntity(financialProvider);
        return this.debtAccountJpaRepository.findAllByFinancialProvider(financialProviderEntity)
                .stream()
                .map(this.debtAccountMapper::toModel)
                .toList();
    }
}
