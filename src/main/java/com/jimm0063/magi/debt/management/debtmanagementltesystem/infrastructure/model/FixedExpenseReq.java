package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class FixedExpenseReq {
    private String name;
    private Double monthlyCost;
    private Integer paymentDay;
    private String userEmail;
    private Integer catalogId;
}
