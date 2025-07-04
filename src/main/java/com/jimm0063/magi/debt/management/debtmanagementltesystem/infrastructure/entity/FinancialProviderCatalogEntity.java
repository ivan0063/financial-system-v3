package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "financial_provider_catalog")
@Setter
@Getter
public class FinancialProviderCatalogEntity implements Serializable {
    @Id
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "financialProviderCatalog")
    private List<FinancialProviderEntity> financialProviders;
}
