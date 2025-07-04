package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class FixedExpenseCatalog implements Serializable {
    private Integer id;
    private String name;
    //private List<FixedExpense> fixedExpenses;
}
