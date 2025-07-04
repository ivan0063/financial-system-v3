package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.DebtAccountStatusUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debt/account")
public class DebtAccountController {
    private final DebtAccountStatusUseCase debtAccountStatusUseCase;

    public DebtAccountController(DebtAccountStatusUseCase debtAccountStatusUseCase) {
        this.debtAccountStatusUseCase = debtAccountStatusUseCase;
    }

    @GetMapping("/status/{debtAccountCode}")
    public ResponseEntity debtAccountStatus(@PathVariable("debtAccountCode") String debtAccountCode) {
        return ResponseEntity.ok(debtAccountStatusUseCase.getStatus(debtAccountCode));
    }
}
