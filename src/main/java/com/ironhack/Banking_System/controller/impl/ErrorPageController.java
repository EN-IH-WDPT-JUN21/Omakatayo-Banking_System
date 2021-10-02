package com.ironhack.Banking_System.controller.impl;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorPageController implements ErrorController {

    private static final String PATH = "/error";

    // Mapping to return custom /error page
    @RequestMapping(value = PATH)
    public String error() {
        return "Sorry, nothing to see here!\nWrong URL or you don't have permission to do whatever you were trying " +
                "to do!!!";
    }

}
