package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;

@Table(name = "debt")
@Entity
@Setter
@Getter
public class DebtEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(name = "operation_date")
    private String operationDate;
    @Column(name = "current_installment")
    private Integer currentInstallment;
    @Column(name = "max_financing_term")
    private Integer maxFinancingTerm;
    @Column(name = "original_amount")
    private Double originalAmount;
    @Column(name = "monthly_payment")
    private Double monthlyPayment;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "debt_account", nullable = false)
    private DebtAccountEntity debtAccount;
}
