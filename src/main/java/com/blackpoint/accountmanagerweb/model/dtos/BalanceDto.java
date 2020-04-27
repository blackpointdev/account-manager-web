package com.blackpoint.accountmanagerweb.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BalanceDto {
    private Long numberOfOperations;
    private BigDecimal balance;

    public BalanceDto() {
        this.numberOfOperations = 0L;
    }
}
