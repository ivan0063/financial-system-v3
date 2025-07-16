package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.FixedExpenseCatalog;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.FixedExpenseCatalogRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.FixedExpenseCatalogEntity;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.mapper.FixedExpenseCatalogMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence.FixedExpenseCatalogJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FixedExpenseCatalogRepositoryAdapter implements FixedExpenseCatalogRepository {
    private final FixedExpenseCatalogJpaRepository fixedExpenseCatalogJpaRepository;
    private final FixedExpenseCatalogMapper fixedExpenseCatalogMapper;

    public FixedExpenseCatalogRepositoryAdapter(FixedExpenseCatalogJpaRepository fixedExpenseCatalogJpaRepository, FixedExpenseCatalogMapper fixedExpenseCatalogMapper) {
        this.fixedExpenseCatalogJpaRepository = fixedExpenseCatalogJpaRepository;
        this.fixedExpenseCatalogMapper = fixedExpenseCatalogMapper;
    }

    @Override
    public List<FixedExpenseCatalog> findAll() {
        return fixedExpenseCatalogJpaRepository.findAll()
                .stream()
                .map(this.fixedExpenseCatalogMapper::toModel)
                .toList();
    }

    @Override
    public Optional<FixedExpenseCatalog> findById(Integer id) {
        return fixedExpenseCatalogJpaRepository.findById(id)
                .map(this.fixedExpenseCatalogMapper::toModel);
    }

    @Override
    public FixedExpenseCatalog save(FixedExpenseCatalog fixedExpenseCatalog) {
        FixedExpenseCatalogEntity fixedExpenseCatalogEntity = fixedExpenseCatalogMapper.toEntity(fixedExpenseCatalog);
        return fixedExpenseCatalogMapper.toModel(this.fixedExpenseCatalogJpaRepository.save(fixedExpenseCatalogEntity));
    }

    @Override
    public void delete(Integer fixedExpenseCatalogId) {
        this.fixedExpenseCatalogJpaRepository.deleteById(fixedExpenseCatalogId);
    }
}
