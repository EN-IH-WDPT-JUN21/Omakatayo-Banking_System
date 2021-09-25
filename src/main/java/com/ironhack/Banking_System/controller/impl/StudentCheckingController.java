package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.dao.StudentChecking;
import com.ironhack.Banking_System.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class StudentCheckingController {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @PostMapping("/new/studentchecking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking newStudentChecking(@RequestBody StudentChecking studentChecking) {
        return studentCheckingRepository.save(studentChecking);
    }
}