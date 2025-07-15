package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderCatalogEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderCatalogMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FinancialProviderCatalogJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class FinancialProviderCatalogRepositoryAdapter implements FinancialProviderCatalogRepository {
    private final FinancialProviderCatalogJpaRepository financialProviderCatalogJpaRepository;
    private final FinancialProviderCatalogMapper financialProviderCatalogMapper;

    public FinancialProviderCatalogRepositoryAdapter(FinancialProviderCatalogJpaRepository financialProviderCatalogJpaRepository, FinancialProviderCatalogMapper financialProviderCatalogMapper) {
        this.financialProviderCatalogJpaRepository = financialProviderCatalogJpaRepository;
        this.financialProviderCatalogMapper = financialProviderCatalogMapper;
    }

    @Override
    public FinancialProviderCatalog save(FinancialProviderCatalog financialProviderCatalog) {
        FinancialProviderCatalogEntity financialProviderCatalogEntity = financialProviderCatalogMapper.toEntity(financialProviderCatalog);
        return this.financialProviderCatalogMapper.toModel(financialProviderCatalogJpaRepository.save(financialProviderCatalogEntity));
    }
}
