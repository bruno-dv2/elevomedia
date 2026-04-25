package com.elevomedia.elevomedia_backend.application.service;

public interface TokenService {

    String gerarToken(String email);

    String extrairEmail(String token);

    boolean isTokenValido(String token, String email);
}
