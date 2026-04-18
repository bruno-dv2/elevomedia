package com.elevomedia.elevomedia_backend.domain.repository;

import com.elevomedia.elevomedia_backend.domain.model.SugestaoConteudo;

import java.util.List;
import java.util.Optional;

public interface SugestaoConteudoRepository {

    SugestaoConteudo salvar(SugestaoConteudo sugestaoConteudo);

    Optional<SugestaoConteudo> buscarPorId(Long id);

    List<SugestaoConteudo> listarPorPlanejamento(Long planejamentoId);

    void deletarPorPlanejamento(Long planejamentoId);
}
