package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFixedExpenseCatalogsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllFixedExpenseUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FixedExpenseCatalogMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateFixedExpenseCatalogReq;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.FixedExpenseReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fixed/expense")
public class FixedExpenseController {
    private final FixedExpenseCatalogRepository fixedExpenseCatalogRepository;
    private final FixedExpenseRepository fixedExpenseRepository;
    private final FixedExpenseCatalogMapper fixedExpenseCatalogMapper;
    private final FindAllFixedExpenseUseCase findAllFixedExpenseUseCase;
    private final FindAllFixedExpenseCatalogsUseCase findAllFixedExpenseCatalogsUseCase;

    public FixedExpenseController(FixedExpenseCatalogRepository fixedExpenseCatalogRepository,
                                  FixedExpenseRepository fixedExpenseRepository,
                                  FixedExpenseCatalogMapper fixedExpenseCatalogMapper,
                                  FindAllFixedExpenseUseCase findAllFixedExpenseUseCase,
                                  FindAllFixedExpenseCatalogsUseCase findAllFixedExpenseCatalogsUseCase) {
        this.fixedExpenseCatalogRepository = fixedExpenseCatalogRepository;
        this.fixedExpenseRepository = fixedExpenseRepository;
        this.fixedExpenseCatalogMapper = fixedExpenseCatalogMapper;
        this.findAllFixedExpenseUseCase = findAllFixedExpenseUseCase;
        this.findAllFixedExpenseCatalogsUseCase = findAllFixedExpenseCatalogsUseCase;
    }

    @GetMapping("/catalog/all")
    public ResponseEntity<List<FixedExpenseCatalog>> getFixedExpenseCatalogs() {
        return ResponseEntity.ok(findAllFixedExpenseCatalogsUseCase.getCatalogList());
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
    public ResponseEntity<FixedExpense> createFixedExpense(@RequestBody FixedExpenseReq createFixedExpenseReq) {
        return ResponseEntity.ok(fixedExpenseRepository.save(createFixedExpenseReq, createFixedExpenseReq.getCatalogId()));
    }

    @PutMapping
    public ResponseEntity<FixedExpense> updateFixedExpense(@RequestBody FixedExpense fixedExpense) {
        return ResponseEntity.ok(fixedExpenseRepository.update(fixedExpense));
    }

    @DeleteMapping("/{fixedExpenseId}")
    public ResponseEntity deleteFixedExpense(@PathVariable Integer fixedExpenseId) {
        fixedExpenseRepository.delete(fixedExpenseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{userEmail}")
    public ResponseEntity<List<FixedExpense>> deleteFixedExpense(@PathVariable String userEmail) {
        return ResponseEntity.ok(findAllFixedExpenseUseCase.getByEmail(userEmail));
    }
}
