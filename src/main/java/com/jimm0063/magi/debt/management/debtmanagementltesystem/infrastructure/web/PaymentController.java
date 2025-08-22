package com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.web;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Payment;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.PaymentRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.DoPayment;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final DoPayment payment;
    private final PaymentRepository paymentRepository;

    public PaymentController(DoPayment payment, PaymentRepository paymentRepository) {
        this.payment = payment;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/do/{debtAccountCode}")
    public ResponseEntity<Payment> doPayment(@PathVariable String debtAccountCode) throws IOException {
        return ResponseEntity.ok(payment.cardPayment(debtAccountCode));
    }

    @GetMapping("/{debtAccountCode}")
    @Transactional
    public ResponseEntity<List<Payment>> getPayments(@PathVariable String debtAccountCode) throws IOException {
        return ResponseEntity.ok(paymentRepository.findByDebtAccountCode(debtAccountCode));
    }

    @GetMapping("/latest/{debtAccountCode}")
    @Transactional
    public ResponseEntity<Payment> getLatestPayment(@PathVariable String debtAccountCode) throws IOException {
        return ResponseEntity.ok(paymentRepository.findLatest(debtAccountCode));
    }
}
