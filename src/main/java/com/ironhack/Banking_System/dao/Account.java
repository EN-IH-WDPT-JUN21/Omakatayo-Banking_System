package com.ironhack.Banking_System.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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

    @ManyToOne
    @JoinColumn(name = "primaryOwner_id")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondaryOwner_id")
    private AccountHolder secondaryOwner;

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

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public Account(Money balance, AccountHolder primaryOwner) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
    }

    public Account(Money balance) {
        this.balance = balance;
    }

}


