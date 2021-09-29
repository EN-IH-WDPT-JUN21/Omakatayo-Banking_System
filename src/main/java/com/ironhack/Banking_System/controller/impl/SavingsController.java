package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1")
public class SavingsController {

    @Autowired
    private SavingsRepository savingsRepository;


}