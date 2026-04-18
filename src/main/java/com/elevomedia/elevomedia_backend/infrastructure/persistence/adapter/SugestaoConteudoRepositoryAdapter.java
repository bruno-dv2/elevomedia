package com.elevomedia.elevomedia_backend.infrastructure.persistence.adapter;

import com.elevomedia.elevomedia_backend.domain.model.SugestaoConteudo;
import com.elevomedia.elevomedia_backend.domain.repository.SugestaoConteudoRepository;
import com.elevomedia.elevomedia_backend.infrastructure.persistence.SugestaoConteudoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SugestaoConteudoRepositoryAdapter implements SugestaoConteudoRepository {

    private final SugestaoConteudoRepositoryJpa repositoryJpa;

    @Override
    public SugestaoConteudo salvar(SugestaoConteudo sugestaoConteudo) {
        return repositoryJpa.save(sugestaoConteudo);
    }

    @Override
    public Optional<SugestaoConteudo> buscarPorId(Long id) {
        return repositoryJpa.findById(id);
    }

    @Override
    public List<SugestaoConteudo> listarPorPlanejamento(Long planejamentoId) {
        return repositoryJpa.findByPlanejamentoId(planejamentoId);
    }

    @Override
    public void deletarPorPlanejamento(Long planejamentoId) {
        repositoryJpa.deleteByPlanejamentoId(planejamentoId);
    }
}
