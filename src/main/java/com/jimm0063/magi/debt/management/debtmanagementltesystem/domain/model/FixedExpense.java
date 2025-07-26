package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FixedExpense {
    private Integer id;
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private Boolean active;
    private DebtSysUser debtSysUser;
    private FixedExpenseCatalog fixedExpenseCatalog;
}
