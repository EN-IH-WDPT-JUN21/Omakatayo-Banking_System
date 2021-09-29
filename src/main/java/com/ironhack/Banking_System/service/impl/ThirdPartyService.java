package com.ironhack.Banking_System.service.impl;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.dao.ThirdParty;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.repository.ThirdPartyRepository;
import com.ironhack.Banking_System.service.interfaces.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ThirdPartyService implements IThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AccountRepository accountRepository;

    private void thirdPartyTransfer(Long userAccountId, Long transferAccountId,
                                    String secretKey,
                                    BigDecimal transferAmount) {
        Account storedUserAccount = accountRepository.findById(userAccountId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found!"));
        Account storedTransferAccount =
                accountRepository.findByIdAndSecretKey(transferAccountId, secretKey).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer account not found!"));

        storedTransferAccount.setBalance(new Money(storedTransferAccount.getBalance().increaseAmount(transferAmount)));
        accountRepository.save(storedTransferAccount);

        storedUserAccount.setBalance(new Money(storedUserAccount.getBalance().decreaseAmount(transferAmount)));
        accountRepository.save(storedUserAccount);
    }
}
