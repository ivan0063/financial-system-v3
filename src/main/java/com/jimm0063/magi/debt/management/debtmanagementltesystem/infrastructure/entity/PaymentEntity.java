package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Setter @Getter
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "amount_type")
    private BigDecimal amountPaid;
    @Lob
    @Column(name = "backup_data")
    private String backupData;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "debt_account", nullable = false)
    private DebtAccountEntity debtAccount;
}
