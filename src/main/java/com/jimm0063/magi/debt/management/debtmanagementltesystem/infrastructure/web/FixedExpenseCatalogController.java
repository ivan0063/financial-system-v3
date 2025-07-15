package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFixedExpense;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.InFixedExpenseCatalog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fixed/expense")
public class FixedExpenseCatalogController {
    private final InFixedExpenseCatalog inFixedExpenseCatalog;
    private final InFixedExpense inFixedExpense;

    public FixedExpenseCatalogController(InFixedExpenseCatalog inFixedExpenseCatalog, InFixedExpense inFixedExpense) {
        this.inFixedExpenseCatalog = inFixedExpenseCatalog;
        this.inFixedExpense = inFixedExpense;
    }

    @PostMapping("/catalog")
    public ResponseEntity<FixedExpenseCatalog> createFixedExpenseCatalog(@RequestBody FixedExpenseCatalog fixedExpenseCatalog) {
        return ResponseEntity.ok(inFixedExpenseCatalog.createUpdateFixedExpenseCatalog(fixedExpenseCatalog));
    }

    @PutMapping("/catalog")
    public ResponseEntity<FixedExpenseCatalog> updateFixedExpenseCatalog(@RequestBody FixedExpenseCatalog fixedExpenseCatalog) {
        return ResponseEntity.ok(inFixedExpenseCatalog.createUpdateFixedExpenseCatalog(fixedExpenseCatalog));
    }

    @PostMapping
    public ResponseEntity<FixedExpense> createFixedExpense(@RequestBody FixedExpense fixedExpense) {
        return ResponseEntity.ok(inFixedExpense.createUpdateFixedExpense(fixedExpense));
    }

    @PutMapping
    public ResponseEntity<FixedExpense> updateFixedExpense(@RequestBody FixedExpense fixedExpense) {
        return ResponseEntity.ok(inFixedExpense.createUpdateFixedExpense(fixedExpense));
    }
}
