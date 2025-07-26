package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class CreateDebtReq {
    private String description;
    private String operationDate;
    private Integer currentInstallment;
    private Integer maxFinancingTerm;
    private Double originalAmount;
    private Double monthlyPayment;
    private Boolean active;
}
