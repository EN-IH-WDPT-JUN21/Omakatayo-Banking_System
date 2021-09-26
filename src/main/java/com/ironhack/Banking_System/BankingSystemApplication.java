package com.ironhack.Banking_System;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.CheckingRepository;
import com.ironhack.Banking_System.repository.RoleRepository;
import com.ironhack.Banking_System.repository.SavingsRepository;
import com.ironhack.Banking_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {

        SpringApplication.run(BankingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Checking checking = new Checking(new Money(new BigDecimal("1000")),
                                         new AccountHolder("Tom Brady",
                                                           "tom@brady.com",
                                                           LocalDate.of(1983, 10, 18),
                                                           new Address("Street", "00-151", "Warsaw"),
                                                           new Address("Street2", "00-100", "Cracow")));
        checkingRepository.save(checking);

        Checking checking2 = new Checking(new Money(new BigDecimal("1000")),
                                          new AccountHolder("Tom Brady",
                                                            "tom@brady.com",
                                                            LocalDate.of(1983, 10, 31),
                                                            new Address("Street", "00-151", "Warsaw"),
                                                            new Address("Street2", "00-100", "Cracow")),
                                          new AccountHolder("John Lennon",
                                                            "john@lennon.com",
                                                            LocalDate.of(2000, 6, 10),
                                                            new Address("Street", "00-151", "Warsaw"),
                                                            new Address("Street2", "00-100", "Cracow")));
        checkingRepository.save(checking2);

        Savings savings = new Savings(new Money(new BigDecimal("1000")),
                                      new AccountHolder("Tom Brady",
                                                        "tom@brady.com",
                                                        LocalDate.of(1973, 1, 3),
                                                        new Address("Street", "00-151", "Warsaw"),
                                                        new Address("Street2", "00-100", "Cracow")),
                                      new AccountHolder("John Lennon",
                                                        "john@lennon.com",
                                                        LocalDate.of(2002, 11, 20),
                                                        new Address("Street", "00-151", "Warsaw"),
                                                        new Address("Street2", "00-100", "Cracow")),
                                      new BigDecimal("0.4"),
                                      new Money(new BigDecimal("500")),
                                      new Money(new BigDecimal("100")));
        savingsRepository.save(savings);

        User userAdmin = new User("admin", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e", null);
        userRepository.save(userAdmin);
        Role roleAdmin = new Role("ADMIN", userAdmin);
        roleRepository.save(roleAdmin);

        User userAccountHolder = new User("accountholder", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8" +
                "/m1wQGXDihaX3e", null);
        userRepository.save(userAccountHolder);
        Role roleAccountHolder = new Role("ACCOUNT_HOLDER", userAccountHolder);
        roleRepository.save(roleAccountHolder);

        User userThirdParty = new User("thirdparty", "$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e",
                                  null);
        userRepository.save(userThirdParty);
        Role roleThirdParty = new Role("THIRD_PARTY", userThirdParty);
        roleRepository.save(roleThirdParty);
    }
}
