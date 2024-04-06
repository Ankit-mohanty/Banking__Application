package com.jtbank.backend.service;

public interface IJWTService {
    String generateToken(String accountNumber);
    /**
     * Return the subject
     * */
    String validateToken(String token);
}
