package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.persistence;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.DebtSysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface DebtSysUserJpaRepository extends JpaRepository<DebtSysUserEntity, String> {
    Optional<DebtSysUserEntity> findByEmailAndActiveTrue(String email);
}
