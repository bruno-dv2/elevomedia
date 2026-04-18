package com.elevomedia.elevomedia_backend.infrastructure.persistence;

import com.elevomedia.elevomedia_backend.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositoryJpa extends JpaRepository<Cliente, Long> {

    List<Cliente> findByUsuarioId(Long usuarioId);
}
