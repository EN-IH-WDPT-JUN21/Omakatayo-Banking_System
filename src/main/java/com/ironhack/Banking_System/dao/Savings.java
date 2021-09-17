package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account {

    private BigDecimal interestRate;
    private BigDecimal monthlyMaintenanceFee;

    public Savings(BigDecimal balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                   Status status, String secretKey, BigDecimal penaltyFee, BigDecimal monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        interestRate = new BigDecimal("0.0025");
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public Savings(BigDecimal balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                   Status status, String secretKey, BigDecimal penaltyFee, BigDecimal interestRate,
                   BigDecimal monthlyMaintenanceFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        setInterestRate(interestRate);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
