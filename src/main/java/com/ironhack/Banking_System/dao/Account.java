package com.ironhack.Banking_System.dao;

import com.ironhack.Banking_System.enums.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Money balance;
    private String primaryOwner;
    private String secondaryOwner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String secretKey;
    private Money penaltyFee;
}


