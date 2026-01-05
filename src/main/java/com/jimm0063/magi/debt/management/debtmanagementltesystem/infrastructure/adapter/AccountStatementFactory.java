package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.AccountStatementDataExtractionUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountStatementFactory {
    private final Map<String, AccountStatementDataExtractionUseCase> strategies;

    public AccountStatementFactory(Map<String, AccountStatementDataExtractionUseCase> strategies) {
        this.strategies = strategies;
    }

    public AccountStatementDataExtractionUseCase getStrategy(AccountStatementType strategyName) {
        AccountStatementDataExtractionUseCase strategy = strategies.get(strategyName.toString());
        if (strategy == null) {
            throw new IllegalArgumentException("No such strategy: " + strategyName);
        }
        return strategy;
    }
}
