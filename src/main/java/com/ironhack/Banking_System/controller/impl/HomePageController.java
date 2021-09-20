package com.ironhack.Banking_System.controller.impl;

import com.ironhack.Banking_System.controller.interfaces.IHomePageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/resources")
@Controller
public class HomePageController implements IHomePageController {

    @RequestMapping("/")
    @ResponseBody()
    public String welcome(HttpServletResponse response){
        // create a cookie
        Cookie cookie = new Cookie("user", "123456");
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //add cookie to response
        response.addCookie(cookie);

        return "index"; //note that this says .html
    }
}
