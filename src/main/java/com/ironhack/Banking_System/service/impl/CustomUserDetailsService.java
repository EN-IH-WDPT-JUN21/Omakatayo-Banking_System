package com.ironhack.Banking_System.service.impl;

import com.ironhack.Banking_System.dao.User;
import com.ironhack.Banking_System.repository.UserRepository;
import com.ironhack.Banking_System.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User does not exists!");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
        return customUserDetails;
    }
}
