package com.ironhack.Banking_System;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.CheckingRepository;
import com.ironhack.Banking_System.repository.SavingsRepository;
import com.ironhack.Banking_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(BankingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Checking checking = new Checking(new Money(new BigDecimal("1000")),
                                         new Owner("Tom", "Brady", 60),
                                         new Owner("John", "Lenon", 55),
                                         new Money(new BigDecimal("100")),
                                         new Money(new BigDecimal("30")));
        checkingRepository.save(checking);

        Savings savings = new Savings(new Money(new BigDecimal("1000")),
                                      new Owner("Maria", "Bonito", 34),
                                      new Owner("Jul", "Tally", 20),
                                      new BigDecimal("0.4"),
                                      new Money(new BigDecimal("500")),
                                      new Money(new BigDecimal("100")));
        savingsRepository.save(savings);

        User user = new User("user", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e", null);
        userRepository.save(user);
    }
}
