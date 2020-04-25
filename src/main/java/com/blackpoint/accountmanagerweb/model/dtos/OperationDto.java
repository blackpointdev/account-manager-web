package com.blackpoint.accountmanagerweb.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationDto {
    private Long id;
    private double balance;
    private String name;
    private Long user_id;
    private String username;
}
