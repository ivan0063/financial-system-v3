package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.DateMismatchException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.ProjectionResponse;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.PaymentProjection;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtProjection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Service
@Slf4j
public class PaymentProjectionService implements PaymentProjection {
    @Override
    public List getDebtProjection(LocalDate to, String email, List<DebtProjection> debtsProjection) {
        Hashtable<String, ProjectionResponse> debtAccountTable = new Hashtable<>();
        LocalDate currentDate = LocalDate.now();

        // calculate the number of month between the current date and the date to be
        if (currentDate.isAfter(to)) throw new DateMismatchException(currentDate, to);

        long monthsBetween = ChronoUnit.MONTHS.between(currentDate, to);
        log.info("monthsBetween: " + monthsBetween);

        // init projection

        for (DebtProjection debtProjection : debtsProjection) {
            ProjectionResponse projectionResponse = debtAccountTable.containsKey(debtProjection.getDebtAccount())
                    ? debtAccountTable.get(debtProjection.getDebtAccount()) : new ProjectionResponse();

            projectionResponse.setDebtAccountCode(debtProjection.getDebtAccount());

            double paid = debtProjection.getMonthlyAmount() * (debtProjection.getMaxInstallment() - debtProjection.getCurrentInstallment());
            projectionResponse.setPaid(projectionResponse.getPaid() + paid);

            projectionResponse.setDebtAccountCode();

        }

        return List.of();
    }
}
