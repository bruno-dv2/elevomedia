package com.elevomedia.elevomedia_backend.domain.repository;

import com.elevomedia.elevomedia_backend.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> buscarPorId(Long id);

    List<Cliente> listarPorUsuario(Long usuarioId);


}
