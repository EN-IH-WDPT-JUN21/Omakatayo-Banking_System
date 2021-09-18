package com.ironhack.Banking_System.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AccountHolder extends UserType{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public AccountHolder(String name, String email, LocalDate dateOfBirth, Address primaryAddress,
                         Address mailingAddress) {
        super(name, email);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public Optional setMailingAddress() {
        return Optional.ofNullable(mailingAddress);
    }
}
