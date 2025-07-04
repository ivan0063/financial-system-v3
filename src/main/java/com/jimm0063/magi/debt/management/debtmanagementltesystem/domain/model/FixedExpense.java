package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FixedExpense implements Serializable {
    private Integer id;
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private Boolean active;
    //private DebtSysUser debtSysUser;
    //private FixedExpenseCatalog fixedExpenseCatalog;
}
