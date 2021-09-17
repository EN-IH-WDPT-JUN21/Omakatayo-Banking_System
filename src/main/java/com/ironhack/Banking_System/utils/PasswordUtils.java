package com.ironhack.Banking_System.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    public static String encryptedPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        System.out.println("Encrypted password:");
        System.out.println(encryptedPassword("password"));
    }
}
