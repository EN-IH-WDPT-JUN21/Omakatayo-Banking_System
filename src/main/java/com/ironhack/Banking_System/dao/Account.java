package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass()
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    @Embedded
    private Money balance;

    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "primary_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "primary_last_name")),
            @AttributeOverride(name = "age", column = @Column(name = "primary_age"))
    })
    @Embedded
    private Owner primaryOwner;

    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "secondary_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "secondary_last_name")),
            @AttributeOverride(name = "age", column = @Column(name = "secondary_age"))
    })
    @Embedded
    private Owner secondaryOwner;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private String secretKey;

    @PrePersist
    public void autoGenerateSecretKey() {
        this.setSecretKey(UUID.randomUUID().toString());
    }

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    @Embedded
    private Money penaltyFee = new Money(new BigDecimal("40"));

    public Account(Money balance, Owner primaryOwner, Owner secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(Money balance, Owner primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public Account(Money balance) {
        this.balance = balance;
    }

}


