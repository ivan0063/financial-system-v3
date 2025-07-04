package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderCatalogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FinancialProviderCatalogMapper {
    FinancialProviderCatalogEntity toEntity(FinancialProviderCatalog financialProviderCatalog);
    FinancialProviderCatalog toModel(FinancialProviderCatalogEntity financialProviderCatalog);
}
