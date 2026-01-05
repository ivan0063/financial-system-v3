package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.LoadDebtList;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.PayOffDebtAccountUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debt/management")
public class DebtManagementController {
    private final LoadDebtList loadDebtList;
    private final PayOffDebtAccountUseCase payOffDebtAccountUseCase;

    public DebtManagementController(LoadDebtList loadDebtList, PayOffDebtAccountUseCase payOffDebtAccountUseCase) {
        this.loadDebtList = loadDebtList;
        this.payOffDebtAccountUseCase = payOffDebtAccountUseCase;
    }

    @PostMapping("/add/{debtAccountCode}")
    public ResponseEntity<List<Debt>> addDebtsToDebtAccount(@PathVariable String debtAccountCode,
                                                @RequestBody List<Debt> debts) {
        return ResponseEntity.ok(loadDebtList.saveUnrepeated(debts, debtAccountCode));
    }

    @PatchMapping("/payOff/{debtAccountCode}")
    public ResponseEntity<List<Debt>> payOffDebts(@PathVariable String debtAccountCode) {
        return ResponseEntity.ok(payOffDebtAccountUseCase.payOffByDebtAccountCode(debtAccountCode));
    }

}
