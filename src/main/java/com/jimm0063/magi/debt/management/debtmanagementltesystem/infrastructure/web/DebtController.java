package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.FindAllDebtsUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.PaymentProjection;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateDebtReq;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.ProjectionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/debt")
public class DebtController {
    private final DebtRepository debtRepository;
    private final DebtMapper debtMapper;
    private final FindAllDebtsUseCase findAllDebtsUseCase;
    private final PaymentProjection paymentProjection;

    public DebtController(DebtRepository debtRepository, DebtMapper debtMapper, FindAllDebtsUseCase findAllDebtsUseCase, PaymentProjection paymentProjection) {
        this.debtRepository = debtRepository;
        this.debtMapper = debtMapper;
        this.findAllDebtsUseCase = findAllDebtsUseCase;
        this.paymentProjection = paymentProjection;
    }

    @PostMapping("/{debtAccountCode}")
    public ResponseEntity<Debt> createDebt(@RequestBody CreateDebtReq createDebtReq, @PathVariable String debtAccountCode) {
        return ResponseEntity.ok(debtRepository.save(debtMapper.toModel(createDebtReq), debtAccountCode));
    }

    @PutMapping
    public ResponseEntity<Debt> updateDebt(@RequestBody Debt debt) {
        return ResponseEntity.ok(debtRepository.update(debt));
    }

    @DeleteMapping("/{debtId}")
    public ResponseEntity<Debt> updateDebt(@PathVariable Integer debtId) {
        debtRepository.delete(debtId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{debtAccountCode}")
    public ResponseEntity<List<Debt>> getAllActiveDebtsByDebtAccount(@PathVariable String debtAccountCode) {
        return ResponseEntity.ok(findAllDebtsUseCase.getActiveByDebtAccount(debtAccountCode));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Debt>> getActiveDebts(@RequestHeader String email) {
        return ResponseEntity.ok(this.debtRepository.findAllDebtsByUser(email));
    }

    @PostMapping("/projection")
    public ResponseEntity<List> postDebtProjection(@RequestBody ProjectionRequest projectionRequest) {
        return ResponseEntity.ok(this.paymentProjection.getDebtProjection(projectionRequest.getTo(), projectionRequest.getEmail(), projectionRequest.getDebtsToProject()));
    }
}
