package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FilterDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.PayOffDebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils.DebtComparatorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService implements FilterDebtsUseCase, PayOffDebtAccount {
    private final DebtRepository debtRepository;

    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    @Override
    public List<Debt> filterAccountStatementDebts(List<Debt> accountStatementDebts, String debtAccountCode) {
        List<Debt> debtAccountDebts = this.debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);
        List<Debt> resultDebts = new ArrayList<>(accountStatementDebts);

        if (debtAccountDebts.isEmpty()) return accountStatementDebts;

        for (Debt accountStatementDebt : accountStatementDebts)
            for (Debt debtAccountDebt : debtAccountDebts)
                if (DebtComparatorUtil.compareDebts(accountStatementDebt, debtAccountDebt))
                    resultDebts.remove(accountStatementDebt);

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
}
