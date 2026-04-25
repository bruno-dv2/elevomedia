package com.elevomedia.elevomedia_backend.infrastructure.security;

import com.elevomedia.elevomedia_backend.application.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService implements TokenService {

    @Value("${app.security.jwt-secret}")
    private String secret;

    @Value("${app.security.jwt-expiration-ms}")
    private long expirationMs;

    private SecretKey secretKey;

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String gerarToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValido(String token, String email) {
        Claims claims = extrairClaims(token);
        return claims.getSubject().equals(email) && !claims.getExpiration().before(new Date());
    }

    private Claims extrairClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
