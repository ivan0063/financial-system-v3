package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FixedExpenseCatalogMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FixedExpenseMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFixedExpenseCatalogReq;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFixedExpenseReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fixed/expense")
public class FixedExpenseController {
    private final FixedExpenseCatalogRepository fixedExpenseCatalogRepository;
    private final FixedExpenseRepository fixedExpenseRepository;
    private final FixedExpenseCatalogMapper fixedExpenseCatalogMapper;
    private final FixedExpenseMapper fixedExpenseMapper;

    public FixedExpenseController(FixedExpenseCatalogRepository fixedExpenseCatalogRepository, FixedExpenseRepository fixedExpenseRepository, FixedExpenseCatalogMapper fixedExpenseCatalogMapper, FixedExpenseMapper fixedExpenseMapper) {
        this.fixedExpenseCatalogRepository = fixedExpenseCatalogRepository;
        this.fixedExpenseRepository = fixedExpenseRepository;
        this.fixedExpenseCatalogMapper = fixedExpenseCatalogMapper;
        this.fixedExpenseMapper = fixedExpenseMapper;
    }

    @PostMapping("/catalog")
    public ResponseEntity<FixedExpenseCatalog> createFixedExpenseCatalog(@RequestBody CreateFixedExpenseCatalogReq createFixedExpenseCatalogReq) {
        return ResponseEntity.ok(fixedExpenseCatalogRepository.save(fixedExpenseCatalogMapper.toModel(createFixedExpenseCatalogReq)
        ));
    }

    @PutMapping("/catalog")
    public ResponseEntity<FixedExpenseCatalog> updateFixedExpenseCatalog(@RequestBody FixedExpenseCatalog fixedExpenseCatalog) {
        return ResponseEntity.ok(fixedExpenseCatalogRepository.save(fixedExpenseCatalog));
    }

    @DeleteMapping("/catalog/{fixedExpenseCatalogId}")
    public ResponseEntity deleteFixedExpenseCatalog(@PathVariable Integer fixedExpenseCatalogId) {
        fixedExpenseCatalogRepository.delete(fixedExpenseCatalogId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<FixedExpense> createFixedExpense(@RequestBody CreateFixedExpenseReq createFixedExpenseReq) {
        return ResponseEntity.ok(fixedExpenseRepository.save(fixedExpenseMapper.toModel(createFixedExpenseReq)));
    }

    @PutMapping
    public ResponseEntity<FixedExpense> updateFixedExpense(@RequestBody FixedExpense fixedExpense) {
        return ResponseEntity.ok(fixedExpenseRepository.save(fixedExpense));
    }

    @DeleteMapping("/{fixedExpenseId}")
    public ResponseEntity deleteFixedExpense(@PathVariable Integer fixedExpenseId) {
        fixedExpenseRepository.delete(fixedExpenseId);
        return ResponseEntity.ok().build();
    }
}
