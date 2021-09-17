package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;
    private String primaryOwner;
    private String secondaryOwner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String secretKey;
    private BigDecimal penaltyFee;

    public Account(BigDecimal balance, String primaryOwner, String secondaryOwner, LocalDate creationDate,
                   Status status, String secretKey, BigDecimal penaltyFee) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creationDate = creationDate;
        this.status = status;
        this.secretKey = secretKey;
        this.penaltyFee = penaltyFee;
    }
}


