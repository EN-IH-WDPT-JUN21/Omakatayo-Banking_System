package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.interfaces.ISystemController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController implements ISystemController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Hello World";
    }
}
