package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter @Getter
public class FinancialProvider {
    private String code;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    private FinancialProviderCatalog financialProviderCatalog;
    private SystemUser systemUser;
    //List<DebtAccount> debtAccounts,
}
