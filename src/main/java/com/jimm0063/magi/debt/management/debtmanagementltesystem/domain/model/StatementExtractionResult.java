package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model;
import java.util.List;

public record StatementExtractionResult(StatementMeta meta, List<Debt> debts) {}