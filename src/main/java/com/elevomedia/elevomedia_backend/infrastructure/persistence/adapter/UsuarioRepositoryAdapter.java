package com.elevomedia.elevomedia_backend.infrastructure.persistence.adapter;

import com.elevomedia.elevomedia_backend.domain.model.Usuario;
import com.elevomedia.elevomedia_backend.domain.repository.UsuarioRepository;
import com.elevomedia.elevomedia_backend.infrastructure.persistence.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioRepositoryJpa repositoryJpa;

    @Override
    public Usuario salvar(Usuario usuario) {
        return repositoryJpa.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repositoryJpa.findByEmail(email);
    }
}
