package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.model.CreateDebtReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debt")
public class DebtController {
    private final DebtRepository debtRepository;
    private final DebtMapper debtMapper;

    public DebtController(DebtRepository debtRepository, DebtMapper debtMapper) {
        this.debtRepository = debtRepository;
        this.debtMapper = debtMapper;
    }

    @PostMapping
    public ResponseEntity<Debt> createDebt(@RequestBody CreateDebtReq createDebtReq) {
        return ResponseEntity.ok(debtRepository.save(debtMapper.toModel(createDebtReq)));
    }

    @PutMapping
    public ResponseEntity<Debt> updateDebt(@RequestBody Debt debt) {
        return ResponseEntity.ok(debtRepository.save(debt));
    }
}
