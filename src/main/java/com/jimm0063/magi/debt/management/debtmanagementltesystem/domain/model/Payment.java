package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class Payment {
    private int id;
    private LocalDateTime createdAt;
    private BigDecimal amountPaid;
    private DebtAccount debtAccount;
    @JsonIgnore
    private String backupData;
}