package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Checking extends Account {

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    @Embedded
    private Money minimumBalance;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    @Embedded
    private Money monthlyMaintenanceFee;

    public Checking(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                    Status status, String secretKey, Money penaltyFee, Money minimumBalance,
                    Money monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public Checking(Money balance, String primaryOwner, String secondaryOwner,
                    String secretKey, Money minimumBalance,
                    Money monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }
}