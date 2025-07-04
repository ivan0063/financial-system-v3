package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtAccountEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtAccountMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DebtRepositoryAdapter implements DebtRepository {
    private final DebtJpaRepository debtJpaRepository;
    private final DebtMapper debtMapper;

    public DebtRepositoryAdapter(DebtJpaRepository debtJpaRepository, DebtMapper debtMapper) {
        this.debtJpaRepository = debtJpaRepository;
        this.debtMapper = debtMapper;
    }

    @Override
    public List<Debt> findAllDebtsByDebtAccountAndActiveTrue(String debtAccountCode) {
        return this.debtJpaRepository.findAllDebtsByDebtAccount_CodeAndActiveTrue(debtAccountCode)
                .stream()
                .map(debtMapper::toModel)
                .toList();
    }
}
