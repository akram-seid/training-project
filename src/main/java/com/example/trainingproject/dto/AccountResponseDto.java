package com.example.trainingproject.dto;

import com.example.trainingproject.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private String accountNumber;
    private AccountType accountType;
    private Long customerId;
}
