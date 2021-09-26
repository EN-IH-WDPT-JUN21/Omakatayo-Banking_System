package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;


@RestController
@RequestMapping("/api/v1")
public class CheckingController {

    @Autowired
    private CheckingRepository checkingRepository;

    /*@GetMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking getById(@PathVariable(name = "id") Long id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);

        return optionalChecking.orElse(null); // optionalCourse.isPresent() ? optionalCourse.get() : null;
    }*/

    @PostMapping("/new/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking newChecking(@RequestBody Checking checking) {
        if (Period.between(checking.getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            checking.setAccountType(AccountType.STUDENT_CHECKING);
            checking.setMinimumBalance(new Money(new BigDecimal("0")));
            checking.setMonthlyMaintenanceFee(new Money(new BigDecimal("0")));
        }
        return checkingRepository.save(checking);
    }
}
