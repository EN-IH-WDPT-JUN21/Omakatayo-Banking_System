package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.AccountType;
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
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account {

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.CREDIT_CARD;

    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    @Embedded
    private Money creditLimit;

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
        interestRate = new BigDecimal("0.2");
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        interestRate = new BigDecimal("0.2");
        setCreditLimit(creditLimit);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate,
                      Money creditLimit) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        setCreditLimit(creditLimit);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
        interestRate = new BigDecimal("0.2");
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Money creditLimit) {
        super(balance, primaryOwner);
        interestRate = new BigDecimal("0.2");
        setCreditLimit(creditLimit);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
        creditLimit = new Money(new BigDecimal("100"));
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, BigDecimal interestRate, Money creditLimit) {
        super(balance, primaryOwner);
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


