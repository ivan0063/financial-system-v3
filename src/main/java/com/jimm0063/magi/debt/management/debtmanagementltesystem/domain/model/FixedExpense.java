package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

public record FixedExpense (
        Integer id,
        String name,
        Double monthlyCost,
        Integer paymentDay,
        Boolean active
        //DebtSysUser debtSysUser,
        //FixedExpenseCatalog fixedExpenseCatalog,
) {
}
