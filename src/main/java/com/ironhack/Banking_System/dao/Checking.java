package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    @Enumerated(EnumType.STRING)
    private AccountType accountType = AccountType.CHECKING;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    @Embedded
    private Money minimumBalance = new Money(new BigDecimal("250"));

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    @Embedded
    private Money monthlyMaintenanceFee = new Money(new BigDecimal("12"));


    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
        setAccountType(accountType);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public Checking(Money balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
        setAccountType(accountType);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public Checking(Money balance) {
        super(balance);
        setAccountType(accountType);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    // Method to set accountType STUDENT_CHECKING when primaryOwner age is less than 24
    public void setAccountType(AccountType accountType) {
        if (Period.between(getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            this.accountType = AccountType.STUDENT_CHECKING;
        }
    }

    // Method to set default value for minimumBalance
    public void setMinimumBalance(Money minimumBalance) {
        if (Period.between(getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            this.minimumBalance = new Money(new BigDecimal("0"));
        }
    }

    // Method to set default value for monthlyMaintenanceFee
    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        if (Period.between(getPrimaryOwner().getDateOfBirth(), LocalDate.now()).getYears() < 24) {
            this.monthlyMaintenanceFee = new Money(new BigDecimal("0"));
        }
    }
}