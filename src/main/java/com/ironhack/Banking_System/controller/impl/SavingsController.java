package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class SavingsController {

    @Autowired
    private SavingsRepository savingsRepository;

    @PostMapping("/new/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings newSavings(@RequestBody Savings savings) {
        return savingsRepository.save(savings);
    }
}