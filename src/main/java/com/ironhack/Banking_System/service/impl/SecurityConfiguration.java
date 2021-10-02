package com.ironhack.Banking_System.service.impl;

import com.ironhack.Banking_System.dao.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/account_holder").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/admin").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/third_party").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/checking").hasAnyRole("ADMIN", "ACCOUNT_HOLDER", "THIRD_PARTY")
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/creditcard").hasAnyRole("ADMIN", "ACCOUNT_HOLDER", "THIRD_PARTY")
            .mvcMatchers(HttpMethod.POST, "/api/v1/new/savings").hasAnyRole("ADMIN", "ACCOUNT_HOLDER", "THIRD_PARTY")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/allaccounts").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/allaccounts/{id}").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/account_holder").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/account_holder/{id}").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/admin").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/third_party").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/myaccounts").hasAnyRole("ADMIN", "ACCOUNT_HOLDER", "THIRD_PARTY")
            .mvcMatchers(HttpMethod.GET, "/api/v1/show/myaccounts/{id}").hasAnyRole("ADMIN", "ACCOUNT_HOLDER",
                                                                                "THIRD_PARTY")
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account_holder/transfer").hasRole("ACCOUNT_HOLDER")
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/third_party/transfer").hasRole("THIRD_PARTY")
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/change_balance/{id}").hasRole("ADMIN")
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/change_status/{id}").hasRole("ADMIN")
            .anyRequest().authenticated();
    }
}
