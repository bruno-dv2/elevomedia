package com.elevomedia.elevomedia_backend.infrastructure.persistence.adapter;

import com.elevomedia.elevomedia_backend.domain.model.Cliente;
import com.elevomedia.elevomedia_backend.domain.repository.ClienteRepository;
import com.elevomedia.elevomedia_backend.infrastructure.persistence.ClienteRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final ClienteRepositoryJpa repositoryJpa;

    @Override
    public Cliente salvar(Cliente cliente) {
        return repositoryJpa.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return repositoryJpa.findById(id);
    }

    @Override
    public List<Cliente> listarPorUsuario(Long usuarioId) {
        return repositoryJpa.findByUsuarioId(usuarioId);
    }
}
