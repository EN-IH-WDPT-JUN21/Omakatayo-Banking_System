package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AccountsDTO;
import com.ironhack.Banking_System.controller.interfaces.ISystemController;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.dao.StudentChecking;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class SystemController implements ISystemController {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountsDTO> getAllAccounts() {
        List<AccountsDTO> accountList = new ArrayList<>();
        for (Checking checking : checkingRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(checking.getClass().getSimpleName(), checking.getId(),
                                                      checking.getBalance(),
                                                      checking.getPrimaryOwner(), checking.getCreationDate(),
                                                      checking.getStatus());
            accountList.add(accountsDTO);
        }

        for (CreditCard creditCard : creditCardRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(creditCard.getClass().getSimpleName(), creditCard.getId(),
                                                      creditCard.getBalance(),
                                                      creditCard.getPrimaryOwner(), creditCard.getCreationDate(),
                                                      creditCard.getStatus());
            accountList.add(accountsDTO);
        }

        for (Savings savings : savingsRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(savings.getClass().getSimpleName(), savings.getId(),
                                                      savings.getBalance(),
                                                      savings.getPrimaryOwner(), savings.getCreationDate(),
                                                      savings.getStatus());
            accountList.add(accountsDTO);
        }

        for (StudentChecking studentChecking : studentCheckingRepository.findAll()) {
            AccountsDTO accountsDTO = new AccountsDTO(studentChecking.getClass().getSimpleName(),
                                                      studentChecking.getId(), studentChecking.getBalance(),
                                                      studentChecking.getPrimaryOwner(), studentChecking.getCreationDate(),
                                                      studentChecking.getStatus());
            accountList.add(accountsDTO);
        }

        //var checking = checkingRepository.findAllIdBalanceName();
        /*accountList.add(checkingRepository.findAllIdBalanceName());
        accountList.add(creditCardRepository.findAllIdBalanceName());
        accountList.add(savingsRepository.findAllIdBalanceName());
        accountList.add(studentCheckingRepository.findAllIdBalanceName());*/
        /*for (Object account : accountList)
            System.out.println("Id: " + account);

        for (int i = 0; i < accountList.size(); i++) {
            System.out.println("Id :" + accountList.get(0));
        }*/

        /*for (int j = 0; j < checking.size(); j++) {
            System.out.println("Id: " + Arrays.toString(checking.get(j)) + " | " +
                               "Balance: " + Arrays.toString(checking.get(1)) + " | " +
                               "Primary owner: " + Arrays.toString(checking.get(2)));
        }*/
        return accountList;
    }
}
