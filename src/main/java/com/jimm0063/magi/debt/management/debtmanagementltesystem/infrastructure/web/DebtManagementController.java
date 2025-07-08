package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.PayOffDebtAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debt/management")
public class DebtManagementController {
    private final DebtAccountRepository debtAccountRepository;
    private final DebtRepository debtRepository;
    private final PayOffDebtAccount payOffDebtAccount;

    public DebtManagementController(DebtAccountRepository debtAccountRepository, DebtRepository debtRepository,
                                    PayOffDebtAccount payOffDebtAccount) {
        this.debtAccountRepository = debtAccountRepository;
        this.debtRepository = debtRepository;
        this.payOffDebtAccount = payOffDebtAccount;
    }

    @PostMapping("/add/{debtAccountCode}")
    public ResponseEntity<List<Debt>> addDebtsToDebtAccount(@PathVariable String debtAccountCode,
                                                @RequestBody List<Debt> debts) {
        DebtAccount debtAccount = this.debtAccountRepository.findDebtAccountByCodeAndActiveTrue(debtAccountCode)
                .orElseThrow(() -> new EntityNotFoundException("debtAccount " + debtAccountCode + " not found"));

        return ResponseEntity.ok(debtRepository.saveAll(debts, debtAccount));
    }

    @PatchMapping("/payOff/{debtAccountCode}")
    public ResponseEntity<List<Debt>> payOffDebts(@PathVariable String debtAccountCode) {
        return ResponseEntity.ok(payOffDebtAccount.payOffByDebtAccountCode(debtAccountCode));
    }

}
