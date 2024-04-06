package com.jtbank.backend.utility;

public class GenerateAccountNumber {
    private GenerateAccountNumber() {}

    public static long generate() {
        long randomNumber = (long) (Math.random() * 10_00_00_000);
        return  1_00_00_00_000l + randomNumber;
    }
}
