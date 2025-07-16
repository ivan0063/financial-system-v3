package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFinancialProviderUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialProviderService implements FindAllFinancialProviderUseCase {
    private final FinancialProviderRepository financialProviderRepository;

    public FinancialProviderService(FinancialProviderRepository financialProviderRepository) {
        this.financialProviderRepository = financialProviderRepository;
    }

    @Override
    public List<FinancialProvider> getActiveByEmail(String userEmail) {
        return this.financialProviderRepository.findAllByEmail(userEmail);
    }
}
