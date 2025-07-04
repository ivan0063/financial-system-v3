package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

public class DebtComparatorUtil {
    public static boolean compareDebts(Debt debt1, Debt debt2) {
        return debt1.getDescription().equals(debt2.getDescription()) &&
                debt1.getOperationDate().equals(debt2.getOperationDate()) &&
                debt1.getMaxFinancingTerm().equals(debt2.getMaxFinancingTerm()) &&
                debt1.getMonthlyPayment().equals(debt2.getMonthlyPayment());
    }
}
