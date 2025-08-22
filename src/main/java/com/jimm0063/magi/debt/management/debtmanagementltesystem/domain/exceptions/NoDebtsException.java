package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions;

public class NoDebtsException extends RuntimeException {
    public NoDebtsException(String debtAccountCode) {
        super("Debt account " + debtAccountCode + " has no debts associated");
    }
}
