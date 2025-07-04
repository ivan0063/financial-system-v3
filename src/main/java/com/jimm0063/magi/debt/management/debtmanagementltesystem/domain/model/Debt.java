package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import java.time.Instant;

public record Debt (
    Integer id,
    String description,
    String operationDate,
    Integer currentInstallment,
    Integer maxFinancingTerm,
    Double originalAmount,
    Double monthlyPayment,
    Instant createdAt,
    Instant updatedAt,
    Boolean active,
    DebtAccount debtAccount
){}
