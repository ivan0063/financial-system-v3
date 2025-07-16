package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProvider;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FinancialProviderCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FinancialProviderRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderCatalogMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FinancialProviderMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFinancialProviderReq;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFinancialProviderCatalogReq;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial/provider")
public class FinancialProviderController {
    private final FinancialProviderCatalogRepository financialProviderCatalogRepository;
    private final FinancialProviderRepository financialProviderRepository;
    private final FinancialProviderMapper financialProviderMapper;
    private final FinancialProviderCatalogMapper financialProviderCatalogMapper;

    public FinancialProviderController(FinancialProviderCatalogRepository financialProviderCatalogRepository, FinancialProviderRepository financialProviderRepository, FinancialProviderMapper financialProviderMapper, FinancialProviderCatalogMapper financialProviderCatalogMapper) {
        this.financialProviderCatalogRepository = financialProviderCatalogRepository;
        this.financialProviderRepository = financialProviderRepository;
        this.financialProviderMapper = financialProviderMapper;
        this.financialProviderCatalogMapper = financialProviderCatalogMapper;
    }

    @PostMapping("/catalog")
    public ResponseEntity<FinancialProviderCatalog> createFinancialProviderCatalog(@RequestBody CreateFinancialProviderCatalogReq createFinancialProviderCatalogReq) {
        return ResponseEntity.ok(this.financialProviderCatalogRepository.save(financialProviderCatalogMapper.toModel(createFinancialProviderCatalogReq)));
    }

    @PutMapping("/catalog")
    public ResponseEntity<FinancialProviderCatalog> updateFinancialProviderCatalog(@RequestBody FinancialProviderCatalog financialProviderCatalog) {
        return ResponseEntity.ok(this.financialProviderCatalogRepository.save(financialProviderCatalog));
    }

    @DeleteMapping("/catalog/{financialProviderCatalogId}")
    public ResponseEntity deleteFinancialProviderCatalog(@PathVariable Integer financialProviderCatalogId) {
        this.financialProviderCatalogRepository.delete(financialProviderCatalogId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<FinancialProvider> createFinancialProvider(@RequestBody CreateFinancialProviderReq createFinancialProviderReq) {
        return ResponseEntity.ok(this.financialProviderRepository.save(financialProviderMapper.toModel(createFinancialProviderReq)));
    }

    @PutMapping
    public ResponseEntity<FinancialProvider> updateFinancialProvider(@Valid @RequestBody FinancialProvider financialProvider) {
        return ResponseEntity.ok(this.financialProviderRepository.save(financialProvider));
    }

    @DeleteMapping("/{financialProviderId}")
    public ResponseEntity deleteFinancialProvider(@PathVariable String financialProviderId) {
        this.financialProviderRepository.delete(financialProviderId);
        return ResponseEntity.ok().build();
    }
}
