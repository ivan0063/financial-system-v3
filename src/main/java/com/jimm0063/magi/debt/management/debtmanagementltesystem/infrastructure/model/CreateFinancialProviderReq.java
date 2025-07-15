package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateFinancialProviderReq {
    private String code;
    private String name;
    private Boolean active;
    private FinancialProviderCatalog financialProviderCatalog;
    private SystemUser systemUser;
}
