package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProjectionResponse {
    private String debtAccountCode;
    private Double monthlyPayment;
    private Double paid;
    private Double remainingDebt;

    public ProjectionResponse() {
        debtAccountCode = "";
        monthlyPayment = 0.0;
        paid = 0.0;
        remainingDebt = 0.0;
    }
}
