package com.elevomedia.elevomedia_backend.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "b8f3a2e9d74c1b6e0f5a8d2c9e4b7f1a3d6e9c2b5f8a1d4e7b0c3f6a9d2e5b8");
        ReflectionTestUtils.setField(jwtService, "expirationMs", 86400000L);
        ReflectionTestUtils.invokeMethod(jwtService, "init");
    }

    @Test
    void deveExtrairEmailCorreto_aposGerarToken() {
        // ACT
        var token = jwtService.gerarToken("bruno@email.com");

        // ASSERT
        assertThat(jwtService.extrairEmail(token)).isEqualTo("bruno@email.com");
    }

    @Test
    void deveRetornarTrue_quandoTokenValido() {
        // ARRANGE
        var token = jwtService.gerarToken("bruno@email.com");

        // ACT + ASSERT
        assertThat(jwtService.isTokenValido(token, "bruno@email.com")).isTrue();
    }

    @Test
    void deveRetornarFalse_quandoEmailDiferente() {
        // ARRANGE
        var token = jwtService.gerarToken("bruno@email.com");

        // ACT + ASSERT
        assertThat(jwtService.isTokenValido(token, "outro@email.com")).isFalse();
    }

    @Test
    void deveLancarExcecao_quandoTokenMalformado() {
        // ACT + ASSERT
        assertThatThrownBy(() -> jwtService.extrairEmail("token.invalido.qualquer"))
                .isInstanceOf(Exception.class);
    }
}
