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

    public StudentChecking(Money balance, Owner primaryOwner, Owner secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
    }

    public StudentChecking(Money balance, Owner primaryOwner) {
        super(balance, primaryOwner);
    }
}
