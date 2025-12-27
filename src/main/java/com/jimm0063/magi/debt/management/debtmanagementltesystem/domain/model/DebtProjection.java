package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
public class DebtProjection implements Serializable {
    private Double acomulatedPayable;
    private Integer currentInstallment;
    private String debtAccount;
    private String description;
    private Integer maxInstallment;
    private Double monthlyAmount;
}
