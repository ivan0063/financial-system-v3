package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFixedExpenseUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedExpenseService implements FindAllFixedExpenseUseCase {
    private final FixedExpenseRepository fixedExpenseRepository;

    public FixedExpenseService(FixedExpenseRepository fixedExpenseRepository) {
        this.fixedExpenseRepository = fixedExpenseRepository;
    }

    @Override
    public List<FixedExpense> getByEmail(String userEmail) {
        return this.fixedExpenseRepository.findAllFixedExpenseByEmailAndActiveTrue(userEmail);
    }
}
