package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AccountTypeDTO;
import com.ironhack.Banking_System.controller.dto.AccountsDTO;
import com.ironhack.Banking_System.controller.interfaces.ISystemController;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class SystemController implements ISystemController {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountsDTO> getAllAccounts() {
        List<AccountsDTO> accountList = new ArrayList<>();
        for (Checking checking : checkingRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(checking.getAccountType(), checking.getId(),
                                                      checking.getBalance(),
                                                      checking.getPrimaryOwner().getName(), checking.getCreationDate(),
                                                      checking.getStatus());
            accountList.add(accountsDTO);
        }
        for (CreditCard creditCard : creditCardRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(creditCard.getAccountType(), creditCard.getId(),
                                                      creditCard.getBalance(),
                                                      creditCard.getPrimaryOwner().getName(), creditCard.getCreationDate(),
                                                      creditCard.getStatus());
            accountList.add(accountsDTO);
        }
        for (Savings savings : savingsRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(savings.getAccountType(), savings.getId(),
                                                      savings.getBalance(),
                                                      savings.getPrimaryOwner().getName(), savings.getCreationDate(),
                                                      savings.getStatus());
            accountList.add(accountsDTO);
        }
        return accountList;
    }
}
