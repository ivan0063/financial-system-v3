package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.UserStatusDashboard;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.application.port.in.GetFinancialStatusUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial/status")
public class FinancialStatusController {
    private final GetFinancialStatusUseCase getFinancialStatusUseCase;

    public FinancialStatusController(GetFinancialStatusUseCase getFinancialStatusUseCase) {
        this.getFinancialStatusUseCase = getFinancialStatusUseCase;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserStatusDashboard> getUserFinancialStatus(@PathVariable String email) {
        return ResponseEntity.ok(this.getFinancialStatusUseCase.getUserStatus(email));
    }
}
