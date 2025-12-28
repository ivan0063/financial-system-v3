package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class PalacioMsiRowModel {
    final String period;
    final String planText;
    final int planMonths;
    final int currentInstallment;
    final int totalInstallments;
    final BigDecimal monthlyPayment;
    final BigDecimal saldoPendiente;

    public PalacioMsiRowModel(String period, String planText, int planMonths, int currentInstallment, int totalInstallments,
                              BigDecimal monthlyPayment, BigDecimal saldoPendiente) {
        this.period = period;
        this.planText = planText;
        this.planMonths = planMonths;
        this.currentInstallment = currentInstallment;
        this.totalInstallments = totalInstallments;
        this.monthlyPayment = monthlyPayment;
        this.saldoPendiente = saldoPendiente;
    }
}
