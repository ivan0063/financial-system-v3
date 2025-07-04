package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import java.time.Instant;

public record FinancialProvider (
         String code,
         String name,
         Instant createdAt,
         Instant updatedAt,
         Boolean active,
         FinancialProviderCatalog financialProviderCatalog,
         DebtSysUser debtSysUser
        //List<DebtAccount> debtAccounts,
){
}
