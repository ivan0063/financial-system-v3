package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateFinancialProviderReq {
    private String code;
    private String name;
    private Boolean active;
    private Integer catalogId;
    private String email;
}
