package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    @GetMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getById(@PathVariable(name = "id") Long id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);

        return optionalChecking.orElse(null); // optionalCourse.isPresent() ? optionalCourse.get() : null;
    }
}
