package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DebtComparatorUtil {
    public static boolean compareDebts(Debt debt1, Debt debt2) {
        if (Optional.ofNullable(debt1.getHashSum()).isEmpty() || Optional.ofNullable(debt2.getHashSum()).isEmpty()) return false;

        return debt1.getHashSum().equals(debt2.getHashSum());
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
