package com.ironhack.Banking_System.controller.interfaces;

import javax.servlet.http.HttpServletResponse;

public interface IHomePageController {
    String welcome(HttpServletResponse response) throws Exception;
}
