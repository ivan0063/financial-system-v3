package com.jimm0063.magi.debt.management.debtmanagementltesystem.application.service;

import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.enums.AccountStatementType;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.exceptions.EntityNotFoundException;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.Debt;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.model.DebtAccount;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.in.DebtAccountRepository;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.domain.port.out.ExtractFromFileUseCase;
import com.jimm0063.magi.debt.management.debtmanagementltesystem.infrastructure.adapter.AccountStatementFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AccountStatementService implements ExtractFromFileUseCase {
    private final DebtAccountRepository debtAccountRepository;
    private final AccountStatementFactory accountStatementFactory;

    public AccountStatementService(DebtAccountRepository debtAccountRepository,
                                   AccountStatementFactory accountStatementFactory) {
        this.debtAccountRepository = debtAccountRepository;
        this.accountStatementFactory = accountStatementFactory;
    }

    @Override
    public List<Debt> extractDebts(MultipartFile file, String cardCode, AccountStatementType accountStatementType) throws IOException {
        DebtAccount debtAccount = debtAccountRepository.findDebtAccountByCode(cardCode)
                .orElseThrow(() -> new EntityNotFoundException("Debt account not found" + cardCode));
        return accountStatementFactory.getStrategy(accountStatementType).extractDebts(file, debtAccount);
    }
}
