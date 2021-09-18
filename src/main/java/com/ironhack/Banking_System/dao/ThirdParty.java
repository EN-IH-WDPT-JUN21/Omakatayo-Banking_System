package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ThirdParty extends UserType{

    private String hashedKey;

    public ThirdParty(String name, String email, String hashedKey) {
        super(name, email);
        setHashedKey(hashedKey);
    }
}
