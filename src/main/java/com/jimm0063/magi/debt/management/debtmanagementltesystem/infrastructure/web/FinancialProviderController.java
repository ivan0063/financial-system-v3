package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFinancialProviderCatalog;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial/provider")
public class FinancialProviderController {
    private final InFinancialProviderCatalog financialProviderCatalog;
    private final InFinancialProvider financialProvider;

    public FinancialProviderController(InFinancialProviderCatalog financialProviderCatalog, InFinancialProvider financialProvider) {
        this.financialProviderCatalog = financialProviderCatalog;
        this.financialProvider = financialProvider;
    }

    @PostMapping("/catalog")
    public ResponseEntity<FinancialProviderCatalog> createFinancialProviderCatalog(@RequestBody FinancialProviderCatalog financialProviderCatalog) {
        return ResponseEntity.ok(this.financialProviderCatalog.createUpdateFinancialProviderCatalog(financialProviderCatalog));
    }

    @PutMapping("/catalog")
    public ResponseEntity<FinancialProviderCatalog> updateFinancialProviderCatalog(@RequestBody FinancialProviderCatalog financialProviderCatalog) {
        return ResponseEntity.ok(this.financialProviderCatalog.createUpdateFinancialProviderCatalog(financialProviderCatalog));
    }

    @PostMapping
    public ResponseEntity<FinancialProvider> createFinancialProvider(@RequestBody FinancialProvider financialProvider) {
        return ResponseEntity.ok(this.financialProvider.createUpdateFinancialProvider(financialProvider));
    }

    @PutMapping
    public ResponseEntity<FinancialProvider> updateFinancialProvider(@Valid @RequestBody FinancialProvider financialProvider) {
        return ResponseEntity.ok(this.financialProvider.createUpdateFinancialProvider(financialProvider));
    }
}
