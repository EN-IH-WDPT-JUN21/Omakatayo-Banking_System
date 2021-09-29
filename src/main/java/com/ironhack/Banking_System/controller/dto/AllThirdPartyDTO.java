package com.ironhack.Banking_System.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllThirdPartyDTO {

    private Long id;
    private String name;
    private String email;
    private int hashedKey;
}
