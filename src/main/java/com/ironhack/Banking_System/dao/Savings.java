package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.AccountType;
import com.ironhack.Banking_System.enums.Status;
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
public class Savings extends Account {

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.SAVINGS;

    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    @Embedded
    private Money minimumBalance;

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
        interestRate = new BigDecimal("0.0025");
        minimumBalance = new Money(new BigDecimal("1000"));
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner);
        setInterestRate(interestRate);
        minimumBalance = new Money(new BigDecimal("1000"));
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money minimumBalance) {
        super(balance, primaryOwner, secondaryOwner);
        interestRate = new BigDecimal("0.0025");
        setMinimumBalance(minimumBalance);
    }

    public Savings(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
        interestRate = new BigDecimal("0.0025");
        minimumBalance = new Money(new BigDecimal("1000"));
    }

    public Savings(Money balance, AccountHolder primaryOwner, BigDecimal interestRate) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
        minimumBalance = new Money(new BigDecimal("1000"));
    }

    public Savings(Money balance, AccountHolder primaryOwner, Money minimumBalance) {
        super(balance, primaryOwner);
        interestRate = new BigDecimal("0.0025");
        setMinimumBalance(minimumBalance);
    }

    public Savings(Money balance, AccountHolder primaryOwner, BigDecimal interestRate, Money minimumBalance) {
        super(balance, primaryOwner);
        setInterestRate(interestRate);
        setMinimumBalance(minimumBalance);
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal("0.5")) <= 0) {
            this.interestRate = interestRate;
        }
        else {
            this.interestRate = new BigDecimal("0.5");
            System.out.println("Interest rate cannot be bigger than 0.5%!\n" +
                               "Setting to maximum value!");
        }
    }

    public void setMinimumBalance(Money minimumBalance) {
        if (minimumBalance.getAmount().compareTo(new BigDecimal("100")) >= 0) {
            this.minimumBalance = minimumBalance;
        }
        else {
            this.minimumBalance = new Money(new BigDecimal("100"));
            System.out.println("Minimum balance cannot be lover than 100 " + minimumBalance.getCurrency() + "!\n" +
                               "Setting to minimum value!");
        }
    }
}
