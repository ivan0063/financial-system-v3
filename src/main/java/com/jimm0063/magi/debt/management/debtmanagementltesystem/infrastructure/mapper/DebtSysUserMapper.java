package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DebtSysUserMapper {
    DebtSysUserEntity toEntity(SystemUser systemUser);

    SystemUser toModel(DebtSysUserEntity debtSysUserEntity);
}
