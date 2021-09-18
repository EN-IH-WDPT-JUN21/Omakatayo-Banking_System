package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

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

    private String primaryOwner;
    private String secondaryOwner;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private String secretKey;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    @Embedded
    private Money penaltyFee; // = new Money(new BigDecimal("40"));

    public Account(Money balance, String primaryOwner, String secondaryOwner, Timestamp creationDate,
                   Status status, String secretKey, Money penaltyFee) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
        this.status = status;
        this.secretKey = secretKey;
        this.penaltyFee = penaltyFee;
    }

    public Account(Money balance, String primaryOwner, String secondaryOwner, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        creationDate = new Timestamp(System.currentTimeMillis());
        status = Status.ACTIVE;
        this.secretKey = secretKey;
        penaltyFee = new Money(new BigDecimal("40"));
    }

}


