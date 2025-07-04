package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FilterDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils.DebtComparatorUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService implements FilterDebtsUseCase {
    private final DebtRepository debtRepository;

    public DebtService(DebtRepository debtRepository) {
        this.debtRepository = debtRepository;
    }

    @Override
    public List<Debt> filterAccountStatementDebts(List<Debt> accountStatementDebts, String debtAccountCode) {
        List<Debt> debtAccountDebts = this.debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);
        List<Debt> resultDebts = new ArrayList<>();

        if(debtAccountDebts.isEmpty()) return accountStatementDebts;

        for (Debt accountStatementDebt :  accountStatementDebts)
            for (Debt debtAccountDebt : debtAccountDebts)
                if(!DebtComparatorUtil.compareDebts(accountStatementDebt, debtAccountDebt))
                    resultDebts.add(accountStatementDebt);

        return resultDebts;
    }
}
