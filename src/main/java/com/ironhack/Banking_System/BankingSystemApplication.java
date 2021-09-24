package com.ironhack.Banking_System;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.CheckingRepository;
import com.ironhack.Banking_System.repository.SavingsRepository;
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

    public static void main(String[] args) {

        SpringApplication.run(BankingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Checking checking = new Checking(new Money(new BigDecimal("1000")),
                                         "Tom",
                                         "John",
                                         "12345",
                                         new Money(new BigDecimal("100")),
                                         new Money(new BigDecimal("30")));
        checkingRepository.save(checking);

        Savings savings = new Savings(new Money(new BigDecimal("1000")),
                                      "Maria",
                                      "Julia",
                                      "098765",
                                      new BigDecimal("0.6"),
                                      new Money(new BigDecimal("500")),
                                      new Money(new BigDecimal("100")));
        savingsRepository.save(savings);
    }
}
