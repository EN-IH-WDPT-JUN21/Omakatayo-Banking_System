package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends UserType{

    private int hashedKey;

    public ThirdParty(String name, String email) {
        super(name, email);
        setHashedKey(hashedKey);
    }

    // Method to create hashedKey
    public void setHashedKey(int hashedKey) {
        this.hashedKey = Objects.hash(getId(), getName(), getEmail());
    }


}
