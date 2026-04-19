package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.NaoEncontradoException;
import com.elevomedia.elevomedia_backend.domain.model.Planejamento;
import com.elevomedia.elevomedia_backend.domain.model.SugestaoConteudo;
import com.elevomedia.elevomedia_backend.domain.repository.SugestaoConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SugestaoConteudoService {

    private final SugestaoConteudoRepository sugestaoConteudoRepository;

    @Transactional
    public SugestaoConteudo criar(Long planejamentoId, SugestaoConteudo sugestao) {
        sugestao.setPlanejamento(new Planejamento(planejamentoId));
        return sugestaoConteudoRepository.salvar(sugestao);
    }

    public SugestaoConteudo buscarPorId(Long id) {
        return sugestaoConteudoRepository.buscarPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Sugestão de conteúdo não encontrada."));
    }

    public List<SugestaoConteudo> listarPorPlanejamento(Long planejamentoId) {
        return sugestaoConteudoRepository.listarPorPlanejamento(planejamentoId);
    }

    @Transactional
    public SugestaoConteudo atualizar(Long id, SugestaoConteudo dadosNovos) {
        SugestaoConteudo sugestao = buscarPorId(id);
        sugestao.atualizarCom(dadosNovos);
        return sugestaoConteudoRepository.salvar(sugestao);
    }

    @Transactional
    public SugestaoConteudo atualizarStatus(Long id, String novoStatus) {
        SugestaoConteudo sugestao = buscarPorId(id);
        sugestao.setStatus(novoStatus);
        return sugestaoConteudoRepository.salvar(sugestao);
    }

    @Transactional
    public void deletarPorPlanejamento(Long planejamentoId) {
        sugestaoConteudoRepository.deletarPorPlanejamento(planejamentoId);
    }
}
