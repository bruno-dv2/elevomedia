package com.elevomedia.elevomedia_backend.infrastructure.persistence;

import com.elevomedia.elevomedia_backend.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
