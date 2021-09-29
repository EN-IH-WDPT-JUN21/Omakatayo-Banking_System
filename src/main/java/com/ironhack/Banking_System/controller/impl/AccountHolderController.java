package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.UserAccountListDTO;
import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.repository.*;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
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

    @PatchMapping("/account_holders/transfer")
    @ResponseStatus(HttpStatus.OK)
    public String accountHolderTransfer(@RequestParam Long userAccountId, @RequestParam Long transferAccountId,
                                      @RequestParam Optional<Long> primaryOwnerId,
                                      @RequestParam Optional<Long> secondaryOwnerId,
                                      @RequestParam BigDecimal transferAmount) {
        if (primaryOwnerId.isPresent() && secondaryOwnerId.isEmpty()) {
            if (accountRepository.findByIdAndPrimaryOwnerId(transferAccountId, primaryOwnerId).isPresent()) {
                if (transferAmount.compareTo(accountRepository.findById(userAccountId).get().getBalance().getAmount()) <= 0) {
                    accountService.accountHolderTransferPrimaryOwnerName(userAccountId, transferAccountId, primaryOwnerId,
                                                                         transferAmount);
                    if (accountRepository.findById(userAccountId).get().getAccountType().equals(AccountType.CHECKING)) {
                        if (checkingRepository.findById(userAccountId).get().getBalance().getAmount().compareTo(checkingRepository.findById(userAccountId).get().getMinimumBalance().getAmount()) < 0) {
                            checkingRepository.findById(userAccountId).get().getBalance().decreaseAmount(transferAmount);
                        }
                    }
                } else {
                    return "Not enough founds on your account!!!";
                }
            }
            else {
                return "User account or transfer account not found!!!";
            }

        }
        else if (primaryOwnerId.isEmpty() && secondaryOwnerId.isPresent()) {
            if (accountRepository.findByIdAndSecondaryOwnerId(transferAccountId, secondaryOwnerId).isPresent()) {
                if (transferAmount.compareTo(accountRepository.findById(userAccountId).get().getBalance().getAmount()) <= 0) {
                    accountService.accountHolderTransferSecondaryOwnerName(userAccountId, transferAccountId, secondaryOwnerId,
                                                                           transferAmount);
                } else {
                    return "Not enough founds on your account!!!";
                }
            }
            else {
                return "User account or transfer account not found!!!";
            }
        }
        return "Jupiii :). Money have been transferred!";
    }

}
