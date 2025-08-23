package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

import java.util.ArrayList;
import java.util.List;

public class DebtComparatorUtil {
    public static boolean compareDebts(Debt debt1, Debt debt2) {
        //debt1.getDescription().equals(debt2.getDescription()) &&
        return debt1.getOperationDate().equals(debt2.getOperationDate()) &&
                debt1.getMaxFinancingTerm().equals(debt2.getMaxFinancingTerm()) &&
                debt1.getMonthlyPayment().equals(debt2.getMonthlyPayment());
    }

    public static List<Debt> filterAccountStatementDebts(List<Debt> debtAccountDebts, List<Debt> accountStatementDebts) {
        if (debtAccountDebts.isEmpty()) return accountStatementDebts;
        List<Debt> resultDebts = new ArrayList<>(accountStatementDebts);

        for (Debt accountStatementDebt : accountStatementDebts)
            for (Debt debtAccountDebt : debtAccountDebts)
                if (compareDebts(accountStatementDebt, debtAccountDebt))
                    resultDebts.remove(accountStatementDebt);

        return resultDebts;
    }
}
