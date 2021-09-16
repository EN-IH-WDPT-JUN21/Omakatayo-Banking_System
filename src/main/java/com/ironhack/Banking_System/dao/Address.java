package com.ironhack.Banking_System.dao;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String streetAddress;
    private String postalCode;
    private String city;
}
