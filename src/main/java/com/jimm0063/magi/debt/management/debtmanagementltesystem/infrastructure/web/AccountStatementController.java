package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.ExtractFromFileUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FilterDebtsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account/statement")
public class AccountStatementController {
    private final ExtractFromFileUseCase extractFromFileUseCase;
    private final FilterDebtsUseCase filterDebtsUseCase;

    public AccountStatementController(ExtractFromFileUseCase extractFromFileUseCase,
                                      FilterDebtsUseCase filterDebtsUseCase) {
        this.extractFromFileUseCase = extractFromFileUseCase;
        this.filterDebtsUseCase = filterDebtsUseCase;
    }

    @PostMapping(path = "/extract/{debtAccountCode}", consumes = "multipart/form-data")
    public ResponseEntity<List<Debt>> extractDebts(@RequestParam("file") MultipartFile accountStatement,
                                                   @PathVariable String debtAccountCode,
                                                   @RequestParam AccountStatementType accountStatementType) throws IOException {
        List<Debt> accountStatementDebts = extractFromFileUseCase.extractDebts(accountStatement, debtAccountCode, accountStatementType);
        List<Debt> filteredAccountStatementDebts = this.filterDebtsUseCase.filterAccountStatementDebts(accountStatementDebts, debtAccountCode);
        return ResponseEntity.ok(filteredAccountStatementDebts);
    }
}
