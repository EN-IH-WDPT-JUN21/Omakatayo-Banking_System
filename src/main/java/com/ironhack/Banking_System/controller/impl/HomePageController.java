package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.interfaces.IHomePageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePageController implements IHomePageController {

    @RequestMapping("/resources/**")
    public String welcome() throws Exception {
        return "index.html"; //note that this says .html
    }
}
