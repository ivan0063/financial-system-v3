package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FinancialProviderCatalog implements Serializable {
    private Integer id;
    private String name;
}
