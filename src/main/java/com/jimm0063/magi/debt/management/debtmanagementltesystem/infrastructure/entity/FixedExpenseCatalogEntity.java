package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Table(name = "fixed_expense_catalog")
@Entity
@Setter
@Getter
public class FixedExpenseCatalogEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "fixedExpenseCatalog")
    private List<FixedExpenseEntity> fixedExpenses;
}
