package com.ironhack.Banking_System.service.impl;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void accountHolderTransferPrimaryOwnerName(Long id, BigDecimal transferAmount) {
        Account storedAccount = accountRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
        storedAccount.setBalance(new Money(storedAccount.getBalance().getAmount().add(transferAmount)));
        accountRepository.save(storedAccount);
    }

    public void accountHolderTransferSecondaryOwnerName(Long id, BigDecimal transferAmount) {
        Account storedAccount = accountRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
        storedAccount.setBalance(new Money(storedAccount.getBalance().getAmount().add(transferAmount)));
        accountRepository.save(storedAccount);
    }
}
