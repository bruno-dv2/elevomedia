package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.CredenciaisInvalidasException;
import com.elevomedia.elevomedia_backend.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public String login(String email, String senha) {
        var usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new CredenciaisInvalidasException("Credenciais inválidas."));

        if (!passwordEncoder.matches(senha, usuario.getSenhaHash())) {
            throw new CredenciaisInvalidasException("Credenciais inválidas.");
        }

        return tokenService.gerarToken(usuario.getEmail());
    }
}
