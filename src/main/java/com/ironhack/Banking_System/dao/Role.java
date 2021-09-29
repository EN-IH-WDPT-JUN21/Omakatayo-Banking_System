package com.ironhack.Banking_System.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

}
