package com.jtbank.backend.dto;

import com.jtbank.backend.constant.AccountType;
import jakarta.validation.constraints.NotNull;

public record UpdateDTO(
        @NotNull(message = "Account holder name should not be Null")
        String accountHolderName,

        @NotNull(message = "About customer should not be Null")
        String aboutCustomer,

        @NotNull(message = "Contact number should not be Null")
        String contactNumber,

        @NotNull(message = "Account type should not be Null")
        AccountType accountType,
        @NotNull(message = "City should not be Null")
        String city,
        @NotNull(message = "State should not be Null")
        String state,
        @NotNull(message = "Country should not be Null")
        String country,
        @NotNull(message = "Zipcode should not be Null")
        String zipcode
) {
}
