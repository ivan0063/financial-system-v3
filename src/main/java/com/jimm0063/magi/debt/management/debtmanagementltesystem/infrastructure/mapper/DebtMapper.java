package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateDebtReq;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DebtMapper {
    DebtEntity toEntity(Debt debt);
    Debt toModel(DebtEntity debtEntity);
    Debt toModel(CreateDebtReq createDebtReq);
}
