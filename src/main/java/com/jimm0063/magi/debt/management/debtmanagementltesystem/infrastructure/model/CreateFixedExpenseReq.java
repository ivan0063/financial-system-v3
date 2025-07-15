package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.SystemUser;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateFixedExpenseReq {
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private Boolean active;
    private SystemUser debtSysUser;
    private FixedExpenseCatalog fixedExpenseCatalog;
}
