package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AccountTypeDTO;
import com.ironhack.Banking_System.controller.dto.AccountsDTO;
import com.ironhack.Banking_System.controller.interfaces.ISystemController;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.dao.StudentChecking;
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
        return accountList;
    }

    /*@PatchMapping("/new/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    private List<String> newAccount(@PathVariable(name = "type") String accountType,
                                            @RequestBody Optional<AccountTypeDTO> accountTypeDTO) {
        List<String> accountList = new ArrayList<>();
        if (accountTypeDTO.isPresent()) {
            if (accountType.equals("checking")) {
                Checking checking = checkingRepository.save(accountTypeDTO.get().getChecking());
                accountList.add(checking.toString());
            }
            if (accountType.equals("checking")) {
                CreditCard creditCard = creditCardRepository.save(accountTypeDTO.get().getCreditCard());
                accountList.add(creditCard.toString());
            }
            if (accountType.equals("checking")) {
                Savings savings = savingsRepository.save(accountTypeDTO.get().getSavings());
                accountList.add(savings.toString());
            }
            if (accountType.equals("checking")) {
                StudentChecking studentChecking = studentCheckingRepository.save(accountTypeDTO.get().getStudentChecking());
                accountList.add(studentChecking.toString());
            }
        }
        return accountList;
    }*/
}
