package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.UserAccountListDTO;
import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.*;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private UserTypeRepository userTypeRepository;


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
            String secondaryOwnerName;
            if (checking.getSecondaryOwner() != null) {
                secondaryOwnerName = checking.getSecondaryOwner().getName();
            }
            else {
                secondaryOwnerName = null;
            }
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(checking.getAccountType(),
                                                                           checking.getId(),
                                                                           checking.getPrimaryOwner().getName(),
                                                                           secondaryOwnerName,
                                                                           checking.getBalance(),
                                                                           checking.getCreationDate(),
                                                                           checking.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        for (CreditCard creditCard : creditCardRepository.findAll()) {
            String secondaryOwnerName;
            if (creditCard.getSecondaryOwner() != null) {
                secondaryOwnerName = creditCard.getSecondaryOwner().getName();
            }
            else {
                secondaryOwnerName = null;
            }
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(creditCard.getAccountType(),
                                                                           creditCard.getId(),
                                                                           creditCard.getPrimaryOwner().getName(),
                                                                           secondaryOwnerName,
                                                                           creditCard.getBalance(),
                                                                           creditCard.getCreationDate(),
                                                                           creditCard.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        for (Savings savings : savingsRepository.findAll()) {
            String secondaryOwnerName;
            if (savings.getSecondaryOwner() != null) {
                secondaryOwnerName = savings.getSecondaryOwner().getName();
            }
            else {
                secondaryOwnerName = null;
            }
            UserAccountListDTO userAccountListDTO = new UserAccountListDTO(savings.getAccountType(),
                                                                           savings.getId(),
                                                                           savings.getPrimaryOwner().getName(),
                                                                           secondaryOwnerName,
                                                                           savings.getBalance(),
                                                                           savings.getCreationDate(),
                                                                           savings.getStatus());
            userAccountListDTOS.add(userAccountListDTO);
        }
        return userAccountListDTOS; // optionalCourse.isPresent() ? optionalCourse.get() : // null;
    }

    @PostMapping("/account_holders/new")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder newAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @PatchMapping("/account_holders/transfer")
    @ResponseStatus(HttpStatus.OK)
    public String accountHolderTransfer(@RequestParam Long id,
                                      /*@RequestParam Optional<String> primaryOwnerName,
                                      @RequestParam Optional<String> secondaryOwnerName,*/
                                      @RequestParam BigDecimal transferAmount) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
            accountService.accountHolderTransferSecondaryOwnerName(id, transferAmount);
        return "Jupi :). Money have been transferred! User ID: " + currentPrincipalName;
    }
}
