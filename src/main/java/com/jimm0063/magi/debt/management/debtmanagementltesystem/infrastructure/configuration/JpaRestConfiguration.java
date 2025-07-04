package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.configuration;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.entity.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class JpaRestConfiguration implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.setBasePath("/jpa");
        config.exposeIdsFor(
                DebtAccountEntity.class,
                DebtEntity.class,
                DebtSysUserEntity.class,
                FinancialProviderCatalogEntity.class,
                FinancialProviderEntity.class,
                FixedExpenseCatalogEntity.class,
                FixedExpenseEntity.class
        );
    }
}
