package com.jtbank.backend.dto;

import com.jtbank.backend.constant.AccountType;

public record AccountResponseDTO(
        long accountNumber,
        String accountHolderName,
        String aboutCustomer,
        String contactNumber,
        String accountEmail,
        double accountBalance,
        AccountType accountType,
        String city,
        String state,
        String country,
        String zipcode
) {
}
