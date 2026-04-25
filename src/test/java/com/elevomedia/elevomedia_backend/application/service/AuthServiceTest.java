package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.CredenciaisInvalidasException;
import com.elevomedia.elevomedia_backend.domain.model.Usuario;
import com.elevomedia.elevomedia_backend.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    TokenService tokenService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthService authService;

    @Test
    void deveLancarCredenciaisInvalidas_quandoEmailNaoExiste() {
        // ARRANGE
        when(usuarioRepository.buscarPorEmail("inexistente@email.com"))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThatThrownBy(() -> authService.login("inexistente@email.com", "qualquersenha"))
                .isInstanceOf(CredenciaisInvalidasException.class)
                .hasMessage("Credenciais inválidas.");

        verify(passwordEncoder, never()).matches(any(), any());
        verify(tokenService, never()).gerarToken(any());
    }

    @Test
    void deveLancarCredenciaisInvalidas_quandoSenhaErrada() {
        // ARRANGE
        var usuario = new Usuario();
        usuario.setEmail("bruno@email.com");
        usuario.setSenhaHash("$2a$hash_qualquer");

        when(usuarioRepository.buscarPorEmail("bruno@email.com"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaerrada", usuario.getSenhaHash()))
                .thenReturn(false);

        // ACT + ASSERT
        assertThatThrownBy(() -> authService.login("bruno@email.com", "senhaerrada"))
                .isInstanceOf(CredenciaisInvalidasException.class)
                .hasMessage("Credenciais inválidas.");

        verify(tokenService, never()).gerarToken(any());
    }

    @Test
    void deveRetornarToken_quandoCredenciaisCorretas() {
        // ARRANGE
        var usuario = new Usuario();
        usuario.setEmail("bruno@email.com");
        usuario.setSenhaHash("$2a$hash_qualquer");

        when(usuarioRepository.buscarPorEmail("bruno@email.com"))
                .thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("senhaCorreta", usuario.getSenhaHash()))
                .thenReturn(true);
        when(tokenService.gerarToken("bruno@email.com"))
                .thenReturn("jwt.token.gerado");

        // ACT
        var token = authService.login("bruno@email.com", "senhaCorreta");

        // ASSERT
        assertThat(token).isEqualTo("jwt.token.gerado");
        verify(tokenService).gerarToken("bruno@email.com");
    }
}
