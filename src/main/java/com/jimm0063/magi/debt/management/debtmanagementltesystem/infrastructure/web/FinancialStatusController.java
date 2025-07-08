package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.UserStatusDashboard;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.GetFinancialStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financial/status")
public class FinancialStatusController {
    private final GetFinancialStatus getFinancialStatus;

    public FinancialStatusController(GetFinancialStatus getFinancialStatus) {
        this.getFinancialStatus = getFinancialStatus;
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserStatusDashboard> getUserFinancialStatus(@PathVariable String email) {
        return ResponseEntity.ok(this.getFinancialStatus.getUserStatus(email));
    }
}
