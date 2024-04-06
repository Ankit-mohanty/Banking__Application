package com.jtbank.backend.service.impl;

import com.jtbank.backend.service.IJWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;

@Service
public class JWTServiceImpl implements IJWTService {
    private final String secret = "385a472fe0ba0c4f085773084a786f59f0942e3e4abca5b0dffa5edbde9b1a57";

    @Override
    public String generateToken(String accountNumber) {
        return Jwts.builder()
                .signWith(getKey())
                .subject(accountNumber)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    private SecretKey getKey() {
        byte bytes[] = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }
}
