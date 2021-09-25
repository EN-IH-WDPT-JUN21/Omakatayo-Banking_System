package com.ironhack.Banking_System.controller.dto;

import com.ironhack.Banking_System.dao.Checking;
import com.ironhack.Banking_System.dao.CreditCard;
import com.ironhack.Banking_System.dao.Savings;
import com.ironhack.Banking_System.dao.StudentChecking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDTO {

    private Checking checking;
    private CreditCard creditCard;
    private Savings savings;
    private StudentChecking studentChecking;

    public AccountTypeDTO(Checking checking) {
        this.checking = checking;
    }
}
