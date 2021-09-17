package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private Long id;

    private BigDecimal balance;
    private String primaryOwner;
    private String secondaryOwner;
    private Timestamp creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String secretKey;
    private Money penaltyFee;

    public Account(BigDecimal balance, String primaryOwner, String secondaryOwner, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        creationDate = new Timestamp(System.currentTimeMillis());
        status = Status.ACTIVE;
        this.secretKey = secretKey;
        penaltyFee = new Money(new BigDecimal("40"));
    }
}


