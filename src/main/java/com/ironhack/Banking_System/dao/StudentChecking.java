package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentChecking extends Account {

    public StudentChecking(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                           Status status, String secretKey, Money penaltyFee) {
        super(balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
    }
}
