package com.ironhack.Banking_System.controller.dto;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDTO {

    private String accountType;
    private Long id;
    private Money balance;
    private String primaryOwner;
    private Timestamp creationDate;
    private Status status;
}
