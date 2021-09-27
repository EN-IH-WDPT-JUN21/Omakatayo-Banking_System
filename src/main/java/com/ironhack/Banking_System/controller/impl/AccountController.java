package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.UserAccountListDTO;
import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.AccountRepository;
import com.ironhack.Banking_System.repository.CheckingRepository;
import com.ironhack.Banking_System.repository.CreditCardRepository;
import com.ironhack.Banking_System.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {



}
