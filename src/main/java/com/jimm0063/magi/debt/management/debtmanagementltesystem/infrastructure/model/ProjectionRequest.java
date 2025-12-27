package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter @Getter
public class ProjectionRequest {
    private LocalDate to;
    private String email;
    private List<DebtProjection> debtsToProject;
}