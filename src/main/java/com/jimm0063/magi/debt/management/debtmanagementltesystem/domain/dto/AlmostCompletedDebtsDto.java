package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlmostCompletedDebtsDto {
    private String code;
    private String description;
    private Double monthlyPayment;
    private Integer currentInstallment;
    private Integer maxFinancingTerm;
}
