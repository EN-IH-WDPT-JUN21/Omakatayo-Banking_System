package com.ironhack.Banking_System.controller.dto;

import com.ironhack.Banking_System.dao.*;
import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO used to show particular properties when showing list of accounts
public class AllAccountListDTO {

    private AccountType accountType;
    private Long id;
    private Money balance;
    private Long primaryOwnerId;
    private String primaryOwnerName;
    private Long secondaryOwnerId;
    private String secondaryOwnerName;
    private LocalDateTime creationDate;
    private Status status;
    private Money minimumBalance;
    private BigDecimal interestRate;
}
