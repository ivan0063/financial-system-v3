package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import java.time.Instant;

public record DebtSysUser (
         String email,
         String name,
         Double salary,
         Double savings,
         Instant createdAt,
         Instant updatedAt,
         Boolean active
        // List<FixedExpense> fixedExpenses,
        // List<FinancialProvider> financialProviders,
){
}
