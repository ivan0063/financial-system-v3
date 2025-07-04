package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
    private String entityId;

    public EntityNotFoundException(String entityId) {
        super("Entity " + entityId + " not found");
    }
}
