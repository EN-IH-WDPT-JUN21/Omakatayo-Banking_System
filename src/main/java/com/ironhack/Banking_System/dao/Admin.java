package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Admin extends UserType{

    public Admin(String name, String email) {
        super(name, email);
    }
}
