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

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        interestRate = new BigDecimal("0.2");
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, String secretKey,
                      Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        interestRate = new BigDecimal("0.2");
        setCreditLimit(creditLimit);
    }

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, String secretKey,
                      BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        setInterestRate(interestRate);
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, String primaryOwner, String secondaryOwner, String secretKey,
                      BigDecimal interestRate, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal("0.1")) >= 0) {
            this.interestRate = interestRate;
        }
        else {
            this.interestRate = new BigDecimal("0.1");
            System.out.println("Interest rate cannot be lower than 0.1%!\n" +
                                       "Setting to minimum value!");
        }
    }

    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(new BigDecimal("100000")) <= 0) {
            this.creditLimit = creditLimit;
        }
        else {
            this.creditLimit = new Money(new BigDecimal("100000"));
            System.out.println("Maximum credit limit cannot be bigger than 100 000 " + creditLimit.getCurrency() +
                               "!\n" +
                               "Setting to minimum value!");
        }
    }
}


