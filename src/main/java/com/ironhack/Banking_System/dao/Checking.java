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
public class Checking extends Account {

    private Money minimumBalance;
    private Money monthlyMaintenanceFee;

    public Checking(Long id, Money balance, String primaryOwner, String secondaryOwner, LocalDate creationDate,
                    Status status, String secretKey, Money penaltyFee, Money minimumBalance,
                    Money monthlyMaintenanceFee) {
        super(id, balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

}