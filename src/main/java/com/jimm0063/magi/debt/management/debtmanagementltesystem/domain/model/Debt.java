package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
public class Debt implements Serializable {
    private Integer id;
    private String description;
    private String operationDate;
    private Integer currentInstallment;
    private Integer maxFinancingTerm;
    private Double originalAmount;
    private Double monthlyPayment;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean active;
    private DebtAccount debtAccount;
}
