package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    private Money interestRate;
    private Money creditLimit;

    public CreditCard(Long id, Money balance, String primaryOwner, String secondaryOwner, LocalDate creationDate,
                      Status status, String secretKey, Money penaltyFee, Money interestRate, Money creditLimit) {
        super(id, balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }
}
