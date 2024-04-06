package com.jtbank.backend.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenerateAccountNumberTest {

    @Test
    void generate() {
        var result = GenerateAccountNumber.generate();
        Assertions.assertTrue(result > 1_00_00_00_000);
    }
}