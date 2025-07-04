package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class DebtAccount {
    private String code;
    private String name;
    private Integer payDay;
    private Double credit;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    private AccountStatementType accountStatementType;
    // FinancialProvider financialProvider,
    // List<Debt> debts,
}
