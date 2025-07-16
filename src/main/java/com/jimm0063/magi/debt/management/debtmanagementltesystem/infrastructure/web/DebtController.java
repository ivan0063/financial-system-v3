package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateDebtReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debt")
public class DebtController {
    private final DebtRepository debtRepository;
    private final DebtMapper debtMapper;
    private final FindAllDebtsUseCase findAllDebtsUseCase;

    public DebtController(DebtRepository debtRepository, DebtMapper debtMapper, FindAllDebtsUseCase findAllDebtsUseCase) {
        this.debtRepository = debtRepository;
        this.debtMapper = debtMapper;
        this.findAllDebtsUseCase = findAllDebtsUseCase;
    }

    @PostMapping
    public ResponseEntity<Debt> createDebt(@RequestBody CreateDebtReq createDebtReq) {
        return ResponseEntity.ok(debtRepository.save(debtMapper.toModel(createDebtReq)));
    }

    @PutMapping
    public ResponseEntity<Debt> updateDebt(@RequestBody Debt debt) {
        return ResponseEntity.ok(debtRepository.save(debt));
    }

    @DeleteMapping("/{debtId}")
    public ResponseEntity<Debt> updateDebt(@PathVariable Integer debtId) {
        debtRepository.delete(debtId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{debtAccountCode}")
    public ResponseEntity<List<Debt>> getAllActiveDebts(@PathVariable String debtAccountCode) {
        return ResponseEntity.ok(findAllDebtsUseCase.getActiveByDebtAccount(debtAccountCode));
    }
}
