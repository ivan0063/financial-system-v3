package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtSysUser;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateFixedExpenseReq {
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private Boolean active;
    private DebtSysUser debtSysUser;
    private FixedExpenseCatalog fixedExpenseCatalog;
}
