package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountListDTO;
import com.ironhack.Banking_System.controller.interfaces.ISystemController;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<AllAccountListDTO> getAllAccounts() {
        List<AllAccountListDTO> allAccountList = new ArrayList<>();
        for (Checking checking : checkingRepository.findAll()) {
            AllAccountListDTO allAccountListDTO = new AllAccountListDTO(checking.getAccountType(), checking.getId(),
                                                                        checking.getBalance(),
                                                                        checking.getPrimaryOwner().getName(), checking.getCreationDate(),
                                                                        checking.getStatus());
            allAccountList.add(allAccountListDTO);
        }
        for (CreditCard creditCard : creditCardRepository.findAll()) {
            AllAccountListDTO allAccountListDTO = new AllAccountListDTO(creditCard.getAccountType(), creditCard.getId(),
                                                                        creditCard.getBalance(),
                                                                        creditCard.getPrimaryOwner().getName(), creditCard.getCreationDate(),
                                                                        creditCard.getStatus());
            allAccountList.add(allAccountListDTO);
        }
        for (Savings savings : savingsRepository.findAll()) {
            AllAccountListDTO allAccountListDTO = new AllAccountListDTO(savings.getAccountType(), savings.getId(),
                                                                        savings.getBalance(),
                                                                        savings.getPrimaryOwner().getName(), savings.getCreationDate(),
                                                                        savings.getStatus());
            allAccountList.add(allAccountListDTO);
        }
        return allAccountList;
    }
}
