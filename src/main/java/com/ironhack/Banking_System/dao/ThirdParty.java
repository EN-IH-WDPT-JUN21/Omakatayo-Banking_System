package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends UserType{

    private String hashedKey;

    public ThirdParty(String name, String email, String hashedKey) {
        super(name, email);
        setHashedKey(hashedKey);
    }
}
