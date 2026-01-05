package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.DebtAccountStatusUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.FindAllDebtAccountUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.out.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.AlmostCompletedDebtsDto;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.DebtAccountStatusDto;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtAccountService implements DebtAccountStatusUseCase, FindAllDebtAccountUseCase {
    private final DebtAccountRepository debtAccountRepository;
    private final DebtRepository debtRepository;

    public DebtAccountService(DebtAccountRepository debtAccountRepository, DebtRepository debtRepository) {
        this.debtAccountRepository = debtAccountRepository;
        this.debtRepository = debtRepository;
    }

    @Override
    public DebtAccountStatusDto getStatus(String debtAccountCode) {
        DebtAccount debtAccount = this.debtAccountRepository.findDebtAccountByCodeAndActiveTrue(debtAccountCode)
                .orElseThrow(() -> new EntityNotFoundException("Debt account with code " + debtAccountCode + " not found"));

        List<Debt> debts = this.debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);

        List<AlmostCompletedDebtsDto> almostCompletedDebts = debts.stream()
                .filter(debt -> (debt.getCurrentInstallment() + 1) >= debt.getMaxFinancingTerm())
                .map(debt -> {
                    AlmostCompletedDebtsDto almostCompletedDebtsDto = new AlmostCompletedDebtsDto();
                    almostCompletedDebtsDto.setCode(debtAccountCode);
                    almostCompletedDebtsDto.setDescription(debt.getDescription());
                    almostCompletedDebtsDto.setMonthlyPayment(debt.getMonthlyPayment());
                    almostCompletedDebtsDto.setCurrentInstallment(debt.getCurrentInstallment());
                    almostCompletedDebtsDto.setMaxFinancingTerm(debt.getMaxFinancingTerm());

                    return almostCompletedDebtsDto;
                })
                .toList();


        Double monthAmount = debts.stream()
                .mapToDouble(debt -> debt.getMonthlyPayment().doubleValue())
                .sum();

        DebtAccountStatusDto debtAccountStatusDto = new DebtAccountStatusDto();
        debtAccountStatusDto.setDebtAccount(debtAccount);
        debtAccountStatusDto.setDebts(debts);
        debtAccountStatusDto.setAlmostCompletedDebts(almostCompletedDebts);
        debtAccountStatusDto.setMonthPayment(monthAmount);

        return debtAccountStatusDto;
    }

    @Override
    public List<DebtAccount> getActiveByFinancialProvider(String financialProviderCode) {
        return debtAccountRepository.findAllByFinancialProviderCodeAndActiveTrue(financialProviderCode);
    }
}
