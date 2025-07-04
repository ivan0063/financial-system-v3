package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter @Getter
public class SystemUser {
    private String email;
    private String name;
    private Double salary;
    private Double savings;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    // List<FixedExpense> fixedExpenses,
    // List<FinancialProvider> financialProviders,
}
