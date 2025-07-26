package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class FixedExpenseReq {
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private String userEmail;
    private FixedExpenseCatalog fixedExpenseCatalog;
}
