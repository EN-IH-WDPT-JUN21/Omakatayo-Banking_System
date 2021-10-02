package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountListDTO;
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
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;


    // Mapping to show accounts created by logged-in User
    @GetMapping("/show/myaccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AllAccountListDTO> getMyAccounts(@CurrentSecurityContext(expression="authentication")
                                                              Authentication authentication) {
        List<AllAccountListDTO> allAccountList = new ArrayList<>();
        // Finding all accounts based on userLogin property
        for (Account account : accountRepository.findByUserLogin(authentication.getName())) {
            AllAccountListDTO allAccountListDTO = new AllAccountListDTO(account.getAccountType(),
                                                                        account.getId(),
                                                                        account.getBalance(),
                                                                        account.getPrimaryOwner().getName(),
                                                                        account.getCreationDate(),
                                                                        account.getStatus(),
                                                                        account.getMinimumBalance(),
                                                                        account.getInterestRate());
            allAccountList.add(allAccountListDTO);
        }
        return allAccountList;
    }


    // Mapping to show details of single account created by logged-in User
    @GetMapping("/show/myaccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> getAccountDetailsByUserLogin(@PathVariable(name = "id") Long id,
                                                  @CurrentSecurityContext(expression=
            "authentication")
            Authentication authentication) {
        return accountRepository.findByIdAndUserLogin(id, authentication.getName());
    }


    // Mapping to show all accounts - only for Admin
    @GetMapping("/show/allaccounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AllAccountListDTO> getAllAccounts() {
        List<AllAccountListDTO> allAccountList = new ArrayList<>();
        // Finding all accounts
        for (Account account : accountRepository.findAll()) {
            AllAccountListDTO allAccountListDTO = new AllAccountListDTO(account.getAccountType(),
                                                                        account.getId(),
                                                                        account.getBalance(),
                                                                        account.getPrimaryOwner().getName(),
                                                                        account.getCreationDate(),
                                                                        account.getStatus(),
                                                                        account.getMinimumBalance(),
                                                                        account.getInterestRate());
            allAccountList.add(allAccountListDTO);
        }
        return allAccountList;
    }


    // Mapping to show details of any account - only for Admin
    @GetMapping("/show/allaccounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> getAccountDetailsById(@PathVariable(name = "id") Long id) {
        return accountRepository.findById(id);
    }


    // Mapping to create new Checking account
    @PostMapping("/new/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking newChecking(@RequestBody Checking checking, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {    // Creates Authentication object with current login details
        if (ChronoUnit.YEARS.between(checking.getPrimaryOwner().getDateOfBirth().atStartOfDay(),
                                     LocalDate.now().atStartOfDay()) < 24) {    // Checking if Checking or StudentChecking account should be created
            checking.setAccountType(AccountType.STUDENT_CHECKING);
            checking.setMinimumBalance(new Money(new BigDecimal("0")));
            checking.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
        }

        // Checking if user is logged-in and if so setting userLogin parameter as username
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            checking.setUserLogin(authentication.getName());
        }
        return checkingRepository.save(checking);
    }


    // Mapping to create new CreditCard account
    @PostMapping("/new/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard newCreditCard(@RequestBody CreditCard creditCard, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {    // Creates Authentication object with current login details

        // Checking if user is logged in and if so setting userLogin parameter as username
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            creditCard.setUserLogin(authentication.getName());
        }
        setCreditLimit(creditCard);
        setInterestRate(creditCard);
        return creditCardRepository.save(creditCard);
    }


    // Mapping to create new Savings account
    @PostMapping("/new/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings newSavings(@RequestBody Savings savings, @CurrentSecurityContext(expression="authentication")
            Authentication authentication) {    // Creates Authentication object with current login details

        // Checking if user is logged in and if so setting userLogin parameter as username
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            savings.setUserLogin(authentication.getName());
        }
        // Setting default values for interestRate and minimumBalance
        setInterestRate(savings);
        setMinimumBalance(savings);

        return savingsRepository.save(savings);
    }


    // Mapping to change account balance based on account id
    @PostMapping("/change_balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setBalance(new Money(balance));
        }
        return "Balance of an account of id: " + id + " was changed to: " + balance;
    }


    // Mapping to change account status based on account id
    @PostMapping("/change_status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setStatus(status);
        }
        return accountRepository.save(storedAccount.get());
    }


    // Method to set Savings account interestRate default values
    private void setInterestRate(Savings savings) {
        BigDecimal interestRate = savings.getInterestRate();
        if (interestRate == null) {
            savings.setInterestRate(new BigDecimal("0.0025"));  // Setting default value to interestRate
        }
        else if (interestRate.compareTo(new BigDecimal("0.5")) > 0) {
            savings.setMinimumBalance(new Money(new BigDecimal("0.5"))); // Setting the highest possible value
        }
    }


    // Method to set Savings account minimumBalance default values
    private void setMinimumBalance(Savings savings) {
        Money minimumBalance = savings.getMinimumBalance();
        if (minimumBalance == null) {
            savings.setMinimumBalance(new Money(new BigDecimal("1000")));   // Setting default value to interestRate
        }
        else if (minimumBalance.getAmount().compareTo(new BigDecimal("100")) < 0) {
            savings.setMinimumBalance(new Money(new BigDecimal("100")));    // Setting the lowest possible value
        }
    }


    // Method to set CreditCard account interestRate default values
    private void setInterestRate(CreditCard creditCard) {
        BigDecimal interestRate = creditCard.getInterestRate();
        if (interestRate == null) {
            creditCard.setInterestRate(new BigDecimal("0.2"));  // Setting default value to interestRate
        }
        else if (interestRate.compareTo(new BigDecimal("0.1")) < 0) {
            creditCard.setMinimumBalance(new Money(new BigDecimal("0.1"))); // Setting the lowest possible value
        }
    }


    // Method to set CreditCard account creditLimit default values
    private void setCreditLimit(CreditCard creditCard) {
        Money creditLimit = creditCard.getCreditLimit();
        if (creditLimit == null) {
            creditCard.setCreditLimit(new Money(new BigDecimal("100")));  // Setting default value to interestRate
        }
        else if (creditLimit.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            creditCard.setMinimumBalance(new Money(new BigDecimal("100000"))); // Setting the highest possible value
        }
    }
}
