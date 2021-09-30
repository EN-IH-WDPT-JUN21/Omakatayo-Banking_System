package com.ironhack.Banking_System.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// DTO used to show particular properties when showing list of account holders
public class AllAccountHoldersDTO {

    private Long id;
    private String name;
    private String email;
}
