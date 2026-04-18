package com.elevomedia.elevomedia_backend.infrastructure.persistence;

import com.elevomedia.elevomedia_backend.domain.model.Planejamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanejamentoRepositoryJpa extends JpaRepository<Planejamento, Long> {

    List<Planejamento> findByClienteId(Long clienteId);

    Optional<Planejamento> findByClienteIdAndMesAndAno(Long clienteId, Integer mes, Integer ano);
}
