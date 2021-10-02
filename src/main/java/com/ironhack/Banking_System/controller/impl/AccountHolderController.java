package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountHoldersDTO;
import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.*;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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


    // Mapping to create new AccountHolder
    @PostMapping("/new/account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder newAccountHolder(@RequestBody AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    // Mapping to show all AccountHolders
    @GetMapping("/show/account_holder")
    @ResponseStatus(HttpStatus.OK)
    public List<AllAccountHoldersDTO> getAllAccountHolders() {
        List<AllAccountHoldersDTO> allAccountHoldersDTOList = new ArrayList<>();
        for (AccountHolder accountHolder : accountHolderRepository.findAll()) {
            AllAccountHoldersDTO allAccountHoldersDTO = new AllAccountHoldersDTO(accountHolder.getId(),
                                                                                 accountHolder.getName(),
                                                                                 accountHolder.getEmail());
            allAccountHoldersDTOList.add(allAccountHoldersDTO);
        }
        return allAccountHoldersDTOList;
    }

    // Mapping to show particular AccountHolder
    @GetMapping("/show/account_holder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AccountHolder> getById(@PathVariable(name = "id") Long id) {
        return accountHolderRepository.findById(id);
    }

    // Mapping to transfer money from AccountHolder to other account
    @PatchMapping("/account_holder/transfer")
    @ResponseStatus(HttpStatus.OK)
    public String accountHolderTransfer(@RequestParam Long userAccountId,
                                        @CurrentSecurityContext(expression="authentication") Authentication userLogin,
                                        @RequestParam Long transferAccountId,
                                        @RequestParam Optional<Long> primaryOwnerId,
                                        @RequestParam Optional<Long> secondaryOwnerId,
                                        @RequestParam BigDecimal transferAmount) {
        Account userAccount = accountRepository.findByIdAndUserLogin(userAccountId, userLogin.getName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found!"));
        Optional<Account> transferAccountPrimary = accountRepository.findByIdAndPrimaryOwnerId(transferAccountId,
                                                                                        primaryOwnerId);
        Optional<Account> transferAccountSecondary = accountRepository.findByIdAndSecondaryOwnerId(transferAccountId,
                                                                                               secondaryOwnerId);
        // Checking is only primaryOwnerId is provided
        if (primaryOwnerId.isPresent() && secondaryOwnerId.isEmpty()) {
            // Checking if transferAccount exists
            if (transferAccountPrimary.isPresent()) {
                boolean senderHasEnoughMoney = transferAmount.compareTo(userAccount.getBalance().getAmount()) <= 0;
                // Checking if there are sufficient funds on userAccount
                if (senderHasEnoughMoney) {
                    // Transfer money
                    transferMoneyPrimaryOwner(userAccountId, transferAccountId, primaryOwnerId, transferAmount);
                    boolean isACheckingAccount = Objects.equals(userAccount.getAccountType().toString(), "CHECKING");
                    boolean isACreditCardAccount = Objects.equals(userAccount.getAccountType().toString(),
                                                              "CREDIT_CARD");
                    // Checking if accountType equals "CHECKING" or "CREDIT_CARD" - only those have minimumBalance
                    if (isACheckingAccount || isACreditCardAccount) {
                        BigDecimal currentBalance = userAccount.getBalance().getAmount();
                        BigDecimal accountMinimumBalance = userAccount.getMinimumBalance().getAmount();
                        boolean balanceIsLessThenMinimum =
                                currentBalance.compareTo(accountMinimumBalance) < 0;
                        // Checking if balance is lower than minimumBalance and if so applying penaltyFee
                        if (balanceIsLessThenMinimum) {
                            applyPenaltyFee(userAccount, currentBalance);
                        }
                    }
                } else {
                    return "Not enough founds on your account!!!";
                }
            } else {
                return "User account or transfer account not found!!!";
            }
        }
        // Checking is only secondaryOwnerId is provided
        else if (primaryOwnerId.isEmpty() && secondaryOwnerId.isPresent()) {
            // Checking if transferAccount exists
            if (transferAccountSecondary.isPresent()) {
                boolean senderHasEnoughMoney = transferAmount.compareTo(userAccount.getBalance().getAmount()) <= 0;
                // Checking if there are sufficient funds on userAccount
                if (senderHasEnoughMoney) {
                    // Transfer money
                    transferMoneySecondaryOwner(userAccountId, transferAccountId, secondaryOwnerId, transferAmount);
                    boolean isACheckingAccount = Objects.equals(userAccount.getAccountType().toString(), "CHECKING");
                    boolean isACreditCardAccount = Objects.equals(userAccount.getAccountType().toString(),
                                                                  "CREDIT_CARD");
                    // Checking if accountType equals "CHECKING" or "CREDIT_CARD" - only those have minimumBalance
                    if (isACheckingAccount || isACreditCardAccount) {
                        BigDecimal currentBalance = userAccount.getBalance().getAmount();
                        BigDecimal accountMinimumBalance = userAccount.getMinimumBalance().getAmount();
                        boolean balanceIsLessThenMinimum =
                                currentBalance.compareTo(accountMinimumBalance) < 0;
                        // Checking if balance is lower than minimumBalance and if so applying penaltyFee
                        if (balanceIsLessThenMinimum) {
                            applyPenaltyFee(userAccount, currentBalance);
                        }
                    }
                } else {
                    return "Not enough founds on your account!!!";
                }
            } else {
                return "User account or transfer account not found!!!";
            }
        }
        return "Jupiii :). Money have been transferred!";
    }

    // Call to service method to transfer money
    private void transferMoneyPrimaryOwner(Long userAccountId,
                                           Long transferAccountId,
                                           Optional<Long> primaryOwnerId,
                                           BigDecimal transferAmount) {
        accountService.accountHolderTransferPrimaryOwnerName(userAccountId,
                                                             transferAccountId,
                                                             primaryOwnerId,
                                                             transferAmount);
    }

    // Call to service method to transfer money
    private void transferMoneySecondaryOwner(Long userAccountId,
                                             Long transferAccountId,
                                             Optional<Long> secondaryOwnerId,
                                             BigDecimal transferAmount) {
        accountService.accountHolderTransferSecondaryOwnerName(userAccountId,
                                                             transferAccountId,
                                                             secondaryOwnerId,
                                                             transferAmount);
    }

    // Method to apply penaltyFee
    private void applyPenaltyFee(Account userAccount, BigDecimal currentBalance) {

        BigDecimal balanceAfterPenaltyFee =
                currentBalance.subtract(new BigDecimal(String.valueOf(userAccount.getPenaltyFee().getAmount())));
        userAccount.setBalance(new Money(balanceAfterPenaltyFee));
        accountRepository.save(userAccount);
        System.out.println("Penalty fee applied to " + userAccount.getId());
    }

}
