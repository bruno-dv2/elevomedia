package com.elevomedia.elevomedia_backend.domain.repository;

import com.elevomedia.elevomedia_backend.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);
}
