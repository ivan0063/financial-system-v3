package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;

import java.time.Instant;


public record DebtAccount (
         String code,
         String name,
         Integer payDay,
         Double credit,
         Instant createdAt,
         Instant updatedAt,
         Boolean active,
         AccountStatementType accountStatementType
        // FinancialProvider financialProvider,
        // List<Debt> debts,
){
}
