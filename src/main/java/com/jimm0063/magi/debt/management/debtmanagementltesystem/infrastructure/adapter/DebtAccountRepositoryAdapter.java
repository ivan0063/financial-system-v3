package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtAccountMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtAccountJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DebtAccountRepositoryAdapter implements DebtAccountRepository {
    private final DebtAccountJpaRepository debtAccountJpaRepository;
    private final DebtAccountMapper debtAccountMapper;

    public DebtAccountRepositoryAdapter(DebtAccountJpaRepository debtAccountJpaRepository, DebtAccountMapper debtAccountMapper) {
        this.debtAccountJpaRepository = debtAccountJpaRepository;
        this.debtAccountMapper = debtAccountMapper;
    }

    @Override
    public Optional<DebtAccount> findDebtAccountByCode(String code) {
        return this.debtAccountJpaRepository.findDebtAccountByCode(code)
                .map(this.debtAccountMapper::toModel);
    }
}
