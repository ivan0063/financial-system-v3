package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtAccountEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtAccountMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.DebtMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.DebtJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DebtRepositoryAdapter implements DebtRepository {
    private final DebtJpaRepository debtJpaRepository;
    private final DebtMapper debtMapper;
    private final DebtAccountMapper debtAccountMapper;

    public DebtRepositoryAdapter(DebtJpaRepository debtJpaRepository, DebtMapper debtMapper,
                                 DebtAccountMapper debtAccountMapper) {
        this.debtJpaRepository = debtJpaRepository;
        this.debtMapper = debtMapper;
        this.debtAccountMapper = debtAccountMapper;
    }

    @Override
    public List<Debt> findAllDebtsByDebtAccountAndActiveTrue(String debtAccountCode) {
        return this.debtJpaRepository.findAllDebtsByDebtAccount_CodeAndActiveTrue(debtAccountCode)
                .stream()
                .map(debtMapper::toModel)
                .toList();
    }

    @Override
    public List<Debt> saveAll(List<Debt> debt, DebtAccount debtAccount) {
        DebtAccountEntity debtAccountEntity = debtAccountMapper.toEntity(debtAccount);
        List<DebtEntity> debtEntities = debt.stream().map(debtMapper::toEntity).toList();
        debtEntities.forEach(debtEntity -> debtEntity.setDebtAccount(debtAccountEntity));
        return this.debtJpaRepository.saveAllAndFlush(debtEntities)
                .stream().map(debtMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<Debt> saveAll(List<Debt> debt) {
        return this.debtJpaRepository.saveAllAndFlush(debt.stream().map(debtMapper::toEntity).toList())
                .stream().map(debtMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Debt save(Debt debt) {
        DebtEntity debtEntity = debtMapper.toEntity(debt);
        return debtMapper.toModel(this.debtJpaRepository.save(debtEntity));
    }
}
