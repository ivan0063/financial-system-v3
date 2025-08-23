package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FilterDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.LoadDebtList;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.PayOffDebtAccountUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils.DebtComparatorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService implements FilterDebtsUseCase, PayOffDebtAccountUseCase, FindAllDebtsUseCase, LoadDebtList {
    private final DebtRepository debtRepository;
    private final DebtAccountRepository debtAccountRepository;

    public DebtService(DebtRepository debtRepository, DebtAccountRepository debtAccountRepository) {
        this.debtRepository = debtRepository;
        this.debtAccountRepository = debtAccountRepository;
    }

    @Override
    public List<Debt> filterAccountStatementDebts(List<Debt> accountStatementDebts, String debtAccountCode) {
        List<Debt> debtAccountDebts = this.debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);
        //List<Debt> resultDebts = new ArrayList<>(accountStatementDebts);

        List<Debt> resultDebts = DebtComparatorUtil.filterAccountStatementDebts(debtAccountDebts, accountStatementDebts);

//        if (debtAccountDebts.isEmpty()) return accountStatementDebts;
//
//        for (Debt accountStatementDebt : accountStatementDebts)
//            for (Debt debtAccountDebt : debtAccountDebts)
//                if (DebtComparatorUtil.compareDebts(accountStatementDebt, debtAccountDebt))
//                    resultDebts.remove(accountStatementDebt);

        return resultDebts;
    }

    @Override
    public List<Debt> payOffByDebtAccountCode(String debtAccountCode) {
        List<Debt> debtsToPayOff = this.debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode)
                .stream()
                .peek(debt -> debt.setActive(false))
                .toList();

        this.debtRepository.saveAll(debtsToPayOff);
        return debtsToPayOff;
    }

    @Override
    public List<Debt> getActiveByDebtAccount(String debtAccountCode) {
        return debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);
    }

    @Override
    public List<Debt> saveUnrepeated(List<Debt> debts, String debtAccountCode) {
        DebtAccount debtAccount = this.debtAccountRepository.findDebtAccountByCodeAndActiveTrue(debtAccountCode)
                .orElseThrow(() -> new EntityNotFoundException("Debt Account " + debtAccountCode));
        List<Debt> debtAccountDebts = debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);

        List<Debt> debtsFound = DebtComparatorUtil.filterAccountStatementDebts(debtAccountDebts, debts)
                .stream()
                .peek(debt -> debt.setDebtAccount(debtAccount))
                .toList();

        return debtRepository.saveAll(debtsFound);
    }
}
