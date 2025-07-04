package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FixedExpenseMapper {
    FixedExpenseEntity toEntity(FixedExpense fixedExpenseEntity);

    FixedExpense toModel(FixedExpenseEntity fixedExpenseEntity);
}
