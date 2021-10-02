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
