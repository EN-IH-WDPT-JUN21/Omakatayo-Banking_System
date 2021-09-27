package com.ironhack.Banking_System.controller.dto;

import com.ironhack.Banking_System.dao.Money;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountListDTO {

    private AccountType accountType;
    private Long id;
    private Money balance;
    private LocalDateTime creationDate;
    private Status status;
}
