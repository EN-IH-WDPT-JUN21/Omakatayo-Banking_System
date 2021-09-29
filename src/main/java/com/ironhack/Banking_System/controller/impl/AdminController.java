package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.enums.Status;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;


    @PostMapping("/change_balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setBalance(new Money(balance));
        }
        return accountRepository.save(storedAccount.get());
    }

    @PostMapping("/change_status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setStatus(status);
        }
        return accountRepository.save(storedAccount.get());
    }

    @PostMapping("/new/account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder newAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @PostMapping("/new/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin newAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PostMapping("/new/third_party")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty create(@RequestBody ThirdParty thirdParty) {
        thirdParty.setHashedKey(Objects.hash(thirdParty.getId(), thirdParty.getName(), thirdParty.getEmail()));
        return thirdPartyRepository.save(thirdParty);
    }

    @PostMapping("/new/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking newChecking(@RequestBody Checking checking, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {
        if (ChronoUnit.YEARS.between(checking.getPrimaryOwner().getDateOfBirth().atStartOfDay(),
                                     LocalDate.now().atStartOfDay()) < 24) {
            checking.setAccountType(AccountType.STUDENT_CHECKING);
            checking.setMinimumBalance(new Money(new BigDecimal("0")));
            checking.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            checking.setUserLogin(authentication.getName());
        }
        return checkingRepository.save(checking);
    }

    @PostMapping("/new/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard newCreditCard(@RequestBody CreditCard creditCard, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            creditCard.setUserLogin(authentication.getName());
        }
        return creditCardRepository.save(creditCard);
    }

    @PostMapping("/new/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings newSavings(@RequestBody Savings savings, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {
        savings.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            savings.setUserLogin(authentication.getName());
        }
        return savingsRepository.save(savings);
    }
}
