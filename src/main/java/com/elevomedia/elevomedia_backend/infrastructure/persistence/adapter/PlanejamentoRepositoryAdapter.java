package com.elevomedia.elevomedia_backend.infrastructure.persistence.adapter;

import com.elevomedia.elevomedia_backend.domain.model.Planejamento;
import com.elevomedia.elevomedia_backend.domain.repository.PlanejamentoRepository;
import com.elevomedia.elevomedia_backend.infrastructure.persistence.PlanejamentoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlanejamentoRepositoryAdapter implements PlanejamentoRepository {

    private final PlanejamentoRepositoryJpa repositoryJpa;

    @Override
    public Planejamento salvar(Planejamento planejamento) {
        return repositoryJpa.save(planejamento);
    }

    @Override
    public Optional<Planejamento> buscarPorId(Long id) {
        return repositoryJpa.findById(id);
    }

    @Override
    public List<Planejamento> listarPorCliente(Long clienteId) {
        return repositoryJpa.findByClienteId(clienteId);
    }

    @Override
    public Optional<Planejamento> buscarPorClienteMesAno(Long clienteId, Integer mes, Integer ano) {
        return repositoryJpa.findByClienteIdAndMesAndAno(clienteId, mes, ano);
    }
}
