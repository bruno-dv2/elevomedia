package com.elevomedia.elevomedia_backend.domain.repository;

import com.elevomedia.elevomedia_backend.domain.model.Planejamento;

import java.util.List;
import java.util.Optional;

public interface PlanejamentoRepository {

    Planejamento salvar(Planejamento planejamento);

    Optional<Planejamento> buscarPorId(Long id);

    List<Planejamento> listarPorCliente(Long clienteId);

    Optional<Planejamento> buscarPorClienteMesAno(Long clienteId, Integer mes, Integer ano);
}
