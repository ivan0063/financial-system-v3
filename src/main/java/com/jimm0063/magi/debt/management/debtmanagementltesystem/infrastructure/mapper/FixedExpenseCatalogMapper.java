package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseCatalogEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFixedExpenseCatalogReq;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FixedExpenseCatalogMapper {
    FixedExpenseCatalogEntity toEntity(FixedExpenseCatalog fixedExpenseCatalog);
    FixedExpenseCatalog toModel(FixedExpenseCatalogEntity fixedExpenseCatalogEntity);
    FixedExpenseCatalog toModel(CreateFixedExpenseCatalogReq fixedExpenseCatalogEntity);
}
