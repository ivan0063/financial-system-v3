package com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DebtAccountStatusDto {
    private DebtAccount debtAccount;
    private Double monthPayment;
    private List<Debt> debts;
    private List<AlmostCompletedDebtsDto> almostCompletedDebts;
}
