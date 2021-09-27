package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.UserAccountListDTO;
import com.ironhack.Banking_System.dao.AccountHolder;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.AccountHolderRepository;
import com.ironhack.Banking_System.repository.CheckingRepository;
import com.ironhack.Banking_System.repository.CreditCardRepository;
import com.ironhack.Banking_System.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AccountHolderController {

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;

    @GetMapping("/account_holders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolder() {
        return accountHolderRepository.findAll();
    }

    @GetMapping("/account_holders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AccountHolder> getById(@PathVariable(name = "id") Long id) {
        return accountHolderRepository.findById(id);
    }

    @GetMapping("/account_holders/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserAccountListDTO> getByPrimaryOwner(@PathVariable(name = "id") Long id) {
        List<UserAccountListDTO> userAccountListDTOS = new ArrayList<>();
        for (Checking checking : checkingRepository.findAll()) {
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(checking.getAccountType(),
                                                                           checking.getId(),
                                                                           checking.getBalance(),
                                                                           checking.getCreationDate(),
                                                                           checking.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        for (CreditCard creditCard : creditCardRepository.findAll()) {
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(creditCard.getAccountType(),
                                                                           creditCard.getId(),
                                                                           creditCard.getBalance(),
                                                                           creditCard.getCreationDate(),
                                                                           creditCard.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        for (Savings savings : savingsRepository.findAll()) {
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(savings.getAccountType(),
                                                                           savings.getId(),
                                                                           savings.getBalance(),
                                                                           savings.getCreationDate(),
                                                                           savings.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        return userAccountListDTOS; // optionalCourse.isPresent() ? optionalCourse.get() : // null;
    }

    @PostMapping("/new/account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder newAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }
}
