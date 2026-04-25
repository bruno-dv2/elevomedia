package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.ConflitoException;
import com.elevomedia.elevomedia_backend.domain.exception.NaoEncontradoException;
import com.elevomedia.elevomedia_backend.domain.model.Planejamento;
import com.elevomedia.elevomedia_backend.domain.repository.PlanejamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanejamentoServiceTest {

    @Mock
    PlanejamentoRepository planejamentoRepository;

    @InjectMocks
    PlanejamentoService planejamentoService;

    @Test
    void deveLancarConflito_quandoJaExistePlanejamentoParaMesAno() {
        // ARRANGE
        var existente = new Planejamento();

        when(planejamentoRepository.buscarPorClienteMesAno(1L, 4, 2026))
                .thenReturn(Optional.of(existente));

        var novo = new Planejamento();
        novo.setMes(4);
        novo.setAno(2026);

        // ACT + ASSERT
        assertThatThrownBy(() -> planejamentoService.criar(1L, novo))
                .isInstanceOf(ConflitoException.class)
                .hasMessage("Já existe um planejamento para este cliente neste mês/ano.");

        verify(planejamentoRepository, never()).salvar(any());
    }

    @Test
    void deveCriarPlanejamento_quandoNaoExisteConflito() {
        // ARRANGE
        var dados = new Planejamento();
        dados.setMes(4);
        dados.setAno(2026);

        when(planejamentoRepository.buscarPorClienteMesAno(1L, 4, 2026))
                .thenReturn(Optional.empty());
        when(planejamentoRepository.salvar(any(Planejamento.class)))
                .thenReturn(dados);

        // ACT
        var resultado = planejamentoService.criar(1L, dados);

        // ASSERT
        assertThat(resultado.getMes()).isEqualTo(4);
        assertThat(resultado.getAno()).isEqualTo(2026);
        verify(planejamentoRepository).salvar(any(Planejamento.class));
    }

    @Test
    void deveLancarNaoEncontrado_quandoPlanejamentoNaoExistePorId() {
        // ARRANGE
        when(planejamentoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThatThrownBy(() -> planejamentoService.buscarPorId(99L))
                .isInstanceOf(NaoEncontradoException.class)
                .hasMessage("Planejamento não encontrado.");
    }

    @Test
    void deveLancarNaoEncontrado_quandoPlanejamentoNaoExistePorClienteMesAno() {
        // ARRANGE
        when(planejamentoRepository.buscarPorClienteMesAno(1L, 4, 2026))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThatThrownBy(() -> planejamentoService.buscarPorClienteMesAno(1L, 4, 2026))
                .isInstanceOf(NaoEncontradoException.class)
                .hasMessage("Planejamento não encontrado para este cliente neste mês/ano.");
    }
}
