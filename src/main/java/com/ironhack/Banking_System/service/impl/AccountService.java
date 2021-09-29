package com.ironhack.Banking_System.service.impl;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void accountHolderTransferPrimaryOwnerName(Long userAccountId,
                                                      Long transferAccountId,
                                                      Optional<Long> primaryOwnerId,
                                                      BigDecimal transferAmount) {
        Account storedUserAccount = accountRepository.findById(userAccountId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found!"));
        Account storedTransferAccount =
                accountRepository.findByIdAndPrimaryOwnerId(transferAccountId, primaryOwnerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer account not found!"));

        storedTransferAccount.setBalance(new Money(storedTransferAccount.getBalance().increaseAmount(transferAmount)));
        accountRepository.save(storedTransferAccount);

        storedUserAccount.setBalance(new Money(storedUserAccount.getBalance().decreaseAmount(transferAmount)));
        accountRepository.save(storedUserAccount);

    }

    public void accountHolderTransferSecondaryOwnerName(Long userAccountId,
                                                        Long transferAccountId,
                                                        Optional<Long> secondaryOwnerId,
                                                        BigDecimal transferAmount) {
        Account storedUserAccount = accountRepository.findById(userAccountId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found!"));
        Account storedTransferAccount =
                accountRepository.findByIdAndSecondaryOwnerId(transferAccountId, secondaryOwnerId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer account not found!"));

        storedTransferAccount.setBalance(new Money(storedTransferAccount.getBalance().increaseAmount(transferAmount)));
        accountRepository.save(storedTransferAccount);

        storedUserAccount.setBalance(new Money(storedUserAccount.getBalance().decreaseAmount(transferAmount)));
        accountRepository.save(storedUserAccount);
    }
}
