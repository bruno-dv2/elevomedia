package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.ConflitoException;
import com.elevomedia.elevomedia_backend.domain.exception.NaoEncontradoException;
import com.elevomedia.elevomedia_backend.domain.model.Cliente;
import com.elevomedia.elevomedia_backend.domain.model.Planejamento;
import com.elevomedia.elevomedia_backend.domain.repository.PlanejamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanejamentoService {

    private final PlanejamentoRepository planejamentoRepository;

    @Transactional
    public Planejamento criar(Long clienteId, Integer mes, Integer ano) {
        planejamentoRepository.buscarPorClienteMesAno(clienteId, mes, ano)
                .ifPresent(p -> { throw new ConflitoException("Já existe um planejamento para este cliente neste mês/ano."); });

        Planejamento planejamento = new Planejamento();
        planejamento.setCliente(new Cliente(clienteId));
        planejamento.setMes(mes);
        planejamento.setAno(ano);

        return planejamentoRepository.salvar(planejamento);
    }

    public Planejamento buscarPorId(Long id) {
        return planejamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Planejamento não encontrado."));
    }

    public List<Planejamento> listarPorCliente(Long clienteId) {
        return planejamentoRepository.listarPorCliente(clienteId);
    }

    public Planejamento buscarPorClienteMesAno(Long clienteId, Integer mes, Integer ano) {
        return planejamentoRepository.buscarPorClienteMesAno(clienteId, mes, ano)
                .orElseThrow(() -> new NaoEncontradoException("Planejamento não encontrado para este cliente neste mês/ano."));
    }

    @Transactional
    public Planejamento atualizarStatus(Long id, String novoStatus) {
        Planejamento planejamento = buscarPorId(id);
        planejamento.setStatus(novoStatus);
        return planejamentoRepository.salvar(planejamento);
    }
}
