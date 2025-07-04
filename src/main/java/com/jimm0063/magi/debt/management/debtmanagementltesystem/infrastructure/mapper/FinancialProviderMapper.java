package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FinancialProviderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FinancialProviderMapper {
    FinancialProviderEntity toEntity(FinancialProvider financialProvider);

    FinancialProvider toModel(FinancialProviderEntity financialProviderEntity);
}
