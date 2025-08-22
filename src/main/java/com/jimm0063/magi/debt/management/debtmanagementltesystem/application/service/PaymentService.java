package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.NoDebtsException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.PaymentRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.DoPayment;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements DoPayment {
    private final PaymentRepository paymentRepository;
    private final DebtAccountRepository debtAccountRepository;
    private final DebtRepository debtRepository;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository, DebtAccountRepository debtAccountRepository, DebtRepository debtRepository, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.debtAccountRepository = debtAccountRepository;
        this.debtRepository = debtRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(rollbackOn =  Exception.class)
    @Override
    public Payment cardPayment(String debtAccountCode) throws JsonProcessingException {
        DebtAccount debtAccount = debtAccountRepository.findDebtAccountByCodeAndActiveTrue(debtAccountCode)
                .orElseThrow(() -> new EntityNotFoundException("Debt account " + debtAccountCode + "not found"));
        List<Debt> debts = debtRepository.findAllDebtsByDebtAccountAndActiveTrue(debtAccountCode);

        if(debts.isEmpty()) throw new NoDebtsException("Debt account " + debtAccountCode + " not found");

        Double amountToPay = debts
                .stream()
                .mapToDouble(Debt::getMonthlyPayment)
                .sum();

        // Update debts
        List<Debt> updatedDebts = debts.stream()
                .peek(debt -> {
                    Integer updatedCurrentInstallment = debt.getCurrentInstallment() + 1;
                    if (updatedCurrentInstallment >= debt.getMaxFinancingTerm()) debt.setActive(false);

                    debt.setCurrentInstallment(updatedCurrentInstallment);
                })
                .toList();

        debtRepository.saveAll(updatedDebts);

        // Creating debts
        Payment payment = new Payment();
        payment.setAmountPaid(amountToPay);
        payment.setDebtAccount(debtAccount);

        // Serialize debts
        String jsonDebts = objectMapper.writeValueAsString(debts);
        payment.setBackupData(jsonDebts);

        payment = paymentRepository.save(payment);
        return payment;
    }
}
