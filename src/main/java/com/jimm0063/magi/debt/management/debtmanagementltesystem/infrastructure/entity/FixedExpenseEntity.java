package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "fixed_expense")
@Entity
@Setter
@Getter
public class FixedExpenseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(name = "monthly_cost")
    private Double monthlyCost;
    @Column(name = "payment_day")
    private Integer paymentDay;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "debt_sys_user", nullable = false)
    private DebtSysUserEntity debtSysUser;
    @ManyToOne
    @JoinColumn(name = "fixed_expenses_catalog", nullable = false)
    private FixedExpenseCatalogEntity fixedExpenseCatalog;
}
