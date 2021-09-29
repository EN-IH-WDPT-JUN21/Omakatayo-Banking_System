package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountListDTO;
import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AllAccountListDTO> getAllAccounts(@CurrentSecurityContext(expression="authentication")
                                                              Authentication authentication) {
        List<AllAccountListDTO> allAccountList = new ArrayList<>();
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
}
