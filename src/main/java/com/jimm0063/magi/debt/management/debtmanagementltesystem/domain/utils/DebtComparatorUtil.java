package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.utils;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;

public class DebtComparatorUtil {
    public static boolean compareDebts(Debt debt1, Debt debt2) {
        return debt1.description().equals(debt2.description()) &&
                debt1.operationDate().equals(debt2.operationDate()) &&
                debt1.maxFinancingTerm().equals(debt2.maxFinancingTerm()) &&
                debt1.monthlyPayment().equals(debt2.monthlyPayment());
    }
}
