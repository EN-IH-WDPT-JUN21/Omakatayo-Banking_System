package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CreditCard extends Account {

    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    @Embedded
    private Money creditLimit;

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                      Status status, String secretKey, Money penaltyFee, BigDecimal interestRate,
                      Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }
}
