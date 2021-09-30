package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.dto.AllAccountHoldersDTO;
import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.enums.Status;
import com.ironhack.Banking_System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    // Mapping to change account balance based on account id
    @PostMapping("/change_balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setBalance(new Money(balance));
        }
        return accountRepository.save(storedAccount.get());
    }

    // Mapping to change account status based on account id
    @PostMapping("/change_status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Optional<Account> storedAccount = accountRepository.findById(id);
        if (storedAccount.isPresent()) {
            storedAccount.get().setStatus(status);
        }
        return accountRepository.save(storedAccount.get());
    }

    // Mapping to create new Admin
    @PostMapping("/new/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin newAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    // Mapping to show all Admins
    @GetMapping("/show/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAllAdmins() {
        List<Admin> allAdmins = adminRepository.findAll();
        return allAdmins;
    }
}
