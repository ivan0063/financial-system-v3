package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "financial_provider")
@Setter
@Getter
public class FinancialProviderEntity implements Serializable {
    @Id
    private String code;
    private String name;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "financial_provider_catalog", nullable = false)
    private FinancialProviderCatalogEntity financialProviderCatalog;
    @ManyToOne
    @JoinColumn(name = "debt_sys_user", nullable = false)
    private DebtSysUserEntity debtSysUser;
    @OneToMany(mappedBy = "financialProvider")
    private List<DebtAccountEntity> debtAccounts;
}
