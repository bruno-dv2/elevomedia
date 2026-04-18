package com.elevomedia.elevomedia_backend.infrastructure.persistence;

import com.elevomedia.elevomedia_backend.domain.model.SugestaoConteudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SugestaoConteudoRepositoryJpa extends JpaRepository<SugestaoConteudo, Long> {

    List<SugestaoConteudo> findByPlanejamentoId(Long planejamentoId);

    void deleteByPlanejamentoId(Long planejamentoId);
}
