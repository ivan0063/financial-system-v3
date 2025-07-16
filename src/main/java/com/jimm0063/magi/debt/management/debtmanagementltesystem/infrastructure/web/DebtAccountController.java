package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.dto.DebtAccountStatusDto;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.DebtAccountStatusUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllDebtAccountUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtAccountMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateDebtAccountReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debt/account")
public class DebtAccountController {
    private final DebtAccountStatusUseCase debtAccountStatusUseCase;
    private final DebtAccountRepository debtAccountRepository;
    private final DebtAccountMapper debtAccountMapper;
    private final FindAllDebtAccountUseCase findAllDebtAccountUseCase;

    public DebtAccountController(DebtAccountStatusUseCase debtAccountStatusUseCase, DebtAccountRepository debtAccountRepository, DebtAccountMapper debtAccountMapper, FindAllDebtAccountUseCase findAllDebtAccountUseCase) {
        this.debtAccountStatusUseCase = debtAccountStatusUseCase;
        this.debtAccountRepository = debtAccountRepository;
        this.debtAccountMapper = debtAccountMapper;
        this.findAllDebtAccountUseCase = findAllDebtAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<DebtAccount> createDebtAccount(@RequestBody CreateDebtAccountReq createDebtAccountReq) {

        return ResponseEntity.ok(this.debtAccountRepository.save(debtAccountMapper.toModel(createDebtAccountReq)));
    }

    @PutMapping
    public ResponseEntity<DebtAccount> updateDebtAccount(@RequestBody DebtAccount debtAccount) {
        return ResponseEntity.ok(this.debtAccountRepository.save(debtAccount));
    }

    @DeleteMapping("/{debtAccountCode}")
    public ResponseEntity deleteDebtAccount(@PathVariable("debtAccountCode") String debtAccountCode) {
        debtAccountRepository.delete(debtAccountCode);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{debtAccountCode}")
    public ResponseEntity<DebtAccountStatusDto> debtAccountStatus(@PathVariable("debtAccountCode") String debtAccountCode) {
        return ResponseEntity.ok(debtAccountStatusUseCase.getStatus(debtAccountCode));
    }

    @GetMapping("/all/{financialProviderCode}")
    public ResponseEntity<List<DebtAccount>> findAllDebtAccount(@PathVariable String financialProviderCode) {
        return ResponseEntity.ok(findAllDebtAccountUseCase.getActiveByFinancialProvider(financialProviderCode));
    }
}
