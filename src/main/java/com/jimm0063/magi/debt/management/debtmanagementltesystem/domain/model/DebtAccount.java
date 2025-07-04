package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class DebtAccount implements Serializable {
    private String code;
    private String name;
    private Integer payDay;
    private Double credit;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    private AccountStatementType accountStatementType;
    //private FinancialProvider financialProvider;
    //private List<Debt> debts;
}
