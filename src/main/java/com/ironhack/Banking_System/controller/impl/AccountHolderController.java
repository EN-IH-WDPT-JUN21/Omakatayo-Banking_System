package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.UserAccountListDTO;
import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.*;
import com.ironhack.Banking_System.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    @PatchMapping("/account_holders/transfer")
    @ResponseStatus(HttpStatus.OK)
    public String accountHolderTransfer(@RequestParam Long userAccountId, @RequestParam Long transferAccountId,
                                        @RequestParam Optional<Long> primaryOwnerId,
                                        @RequestParam Optional<Long> secondaryOwnerId,
                                        @RequestParam BigDecimal transferAmount) {
        Optional<Account> userAccount = accountRepository.findById(userAccountId);
        Optional<Account> transferAccountPrimary = accountRepository.findByIdAndPrimaryOwnerId(transferAccountId,
                                                                                        primaryOwnerId);
        Optional<Account> transferAccountSecondary = accountRepository.findByIdAndSecondaryOwnerId(transferAccountId,
                                                                                               secondaryOwnerId);
        if (primaryOwnerId.isPresent() && secondaryOwnerId.isEmpty()) {
            if (transferAccountPrimary.isPresent()) {
                boolean senderHasEnoughMoney = transferAmount.compareTo(userAccount.get().getBalance().getAmount()) <= 0;
                if (senderHasEnoughMoney) {
                    transferMoneyPrimaryOwner(userAccountId, transferAccountId, primaryOwnerId, transferAmount);
                    boolean isACheckingAccount = Objects.equals(userAccount.get().getAccountType().toString(), "CHECKING");
                    boolean isACreditCardAccount = Objects.equals(userAccount.get().getAccountType().toString(),
                                                              "CREDIT_CARD");
                    if (isACheckingAccount || isACreditCardAccount) {
                        BigDecimal currentBalance = userAccount.get().getBalance().getAmount();
                        BigDecimal accountMinimumBalance = userAccount.get().getMinimumBalance().getAmount();
                        boolean balanceIsLessThenMinimum =
                                currentBalance.compareTo(accountMinimumBalance) < 0;
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
        else if (primaryOwnerId.isEmpty() && secondaryOwnerId.isPresent()) {
            if (transferAccountSecondary.isPresent()) {
                boolean senderHasEnoughMoney = transferAmount.compareTo(userAccount.get().getBalance().getAmount()) <= 0;
                if (senderHasEnoughMoney) {
                    transferMoneySecondaryOwner(userAccountId, transferAccountId, secondaryOwnerId, transferAmount);
                    boolean isACheckingAccount = Objects.equals(userAccount.get().getAccountType().toString(), "CHECKING");
                    boolean isACreditCardAccount = Objects.equals(userAccount.get().getAccountType().toString(),
                                                                  "CREDIT_CARD");
                    if (isACheckingAccount || isACreditCardAccount) {
                        BigDecimal currentBalance = userAccount.get().getBalance().getAmount();
                        BigDecimal accountMinimumBalance = userAccount.get().getMinimumBalance().getAmount();
                        boolean balanceIsLessThenMinimum =
                                currentBalance.compareTo(accountMinimumBalance) < 0;
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

    private void transferMoneyPrimaryOwner(Long userAccountId,
                                           Long transferAccountId,
                                           Optional<Long> primaryOwnerId,
                                           BigDecimal transferAmount) {
        accountService.accountHolderTransferPrimaryOwnerName(userAccountId,
                                                             transferAccountId,
                                                             primaryOwnerId,
                                                             transferAmount);
    }

    private void transferMoneySecondaryOwner(Long userAccountId,
                                             Long transferAccountId,
                                             Optional<Long> secondaryOwnerId,
                                             BigDecimal transferAmount) {
        accountService.accountHolderTransferSecondaryOwnerName(userAccountId,
                                                             transferAccountId,
                                                             secondaryOwnerId,
                                                             transferAmount);
    }

    private void applyPenaltyFee(Optional<Account> userAccount, BigDecimal currentBalance) {

        Account accountToUpdate = userAccount.get();
        BigDecimal balanceAfterPenaltyFee =
                currentBalance.subtract(new BigDecimal(String.valueOf(accountToUpdate.getPenaltyFee().getAmount())));
        accountToUpdate.setBalance(new Money(balanceAfterPenaltyFee));
        accountRepository.save(accountToUpdate);
        System.out.println("Penalty fee applied to " + accountToUpdate.getId());
    }

}
