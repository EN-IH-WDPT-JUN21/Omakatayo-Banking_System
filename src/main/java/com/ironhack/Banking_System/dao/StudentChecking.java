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
public class StudentChecking extends Account {

    public StudentChecking(Long id, Money balance, String primaryOwner, String secondaryOwner, LocalDate creationDate,
                           Status status, String secretKey, Money penaltyFee) {
        super(id, balance, primaryOwner, secondaryOwner, creationDate, status, secretKey, penaltyFee);
    }
}
