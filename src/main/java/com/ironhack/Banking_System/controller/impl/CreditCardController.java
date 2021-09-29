package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CreditCardController {

    @Autowired
    private CreditCardRepository creditCardRepository;


}