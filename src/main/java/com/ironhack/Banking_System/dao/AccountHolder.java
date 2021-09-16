package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends UserType{

    private LocalDate dateOfBirth;

    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "primary_street")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "primary_postal")),
            @AttributeOverride(name = "city", column = @Column(name = "primary_city"))
    })
    @Embedded
    private Address primaryAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "mailing_street")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "mailing_postal")),
            @AttributeOverride(name = "city", column = @Column(name = "mailing_city"))
    })
    @Embedded
    private Address mailingAddress;

    public AccountHolder(Long id, String name, String email, LocalDate dateOfBirth, Address primaryAddress,
                         Address mailingAddress) {
        super(id, name, email);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public Optional getMailingAddress() {
        return Optional.ofNullable(mailingAddress);
    }
}
