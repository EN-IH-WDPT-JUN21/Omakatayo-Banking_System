package com.ironhack.Banking_System.dao;

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
public class Savings extends Account {

    private BigDecimal interestRate;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    @Embedded
    private Money monthlyMaintenanceFee;

    public Savings(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                   Status status, String secretKey, Money penaltyFee, Money monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        interestRate = new BigDecimal("0.0025");
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public Savings(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                   Status status, String secretKey, Money penaltyFee, BigDecimal interestRate,
                   Money monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        setInterestRate(interestRate);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
