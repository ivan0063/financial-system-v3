package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions;

import java.time.LocalDate;

public class DateMismatchException extends RuntimeException {
    public DateMismatchException(LocalDate from, LocalDate to) {
        super(String.format("Date: from %s is before date to %s", from, to));
    }
}
