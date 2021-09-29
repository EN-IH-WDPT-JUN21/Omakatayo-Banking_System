package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.Account;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;


@RestController
@RequestMapping("/api/v1")
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountRepository accountRepository;

    /*@GetMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getById(@PathVariable(name = "id") Long id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);

        return optionalChecking.orElse(null); // optionalCourse.isPresent() ? optionalCourse.get() : null;
    }*/



}
