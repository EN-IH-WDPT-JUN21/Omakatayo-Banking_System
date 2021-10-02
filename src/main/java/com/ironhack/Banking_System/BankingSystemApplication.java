package com.ironhack.Banking_System;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


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
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public static void main(String[] args) {

        SpringApplication.run(BankingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) {

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

        System.out.println("\n\n\n" +
                           "Hi. Thank you for reviewing my Banking System Application :)\n" +
                           "For various reasons I wasn't able to implement all functionalities and tests but still " +
                           "hope you'll have fun testing it.\n" +
                           "Any feedback is welcomed :)");
    }
}
