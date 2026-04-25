package com.elevomedia.elevomedia_backend.application.service;

import com.elevomedia.elevomedia_backend.domain.exception.NaoEncontradoException;
import com.elevomedia.elevomedia_backend.domain.model.SugestaoConteudo;
import com.elevomedia.elevomedia_backend.domain.repository.SugestaoConteudoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SugestaoConteudoServiceTest {

    @Mock
    SugestaoConteudoRepository sugestaoConteudoRepository;

    @InjectMocks
    SugestaoConteudoService sugestaoConteudoService;

    @Test
    void deveLancarNaoEncontrado_quandoSugestaoNaoExistePorId() {
        // ARRANGE
        when(sugestaoConteudoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThatThrownBy(() -> sugestaoConteudoService.buscarPorId(99L))
                .isInstanceOf(NaoEncontradoException.class)
                .hasMessage("Sugestão de conteúdo não encontrada.");
    }

    @Test
    void deveDeletar_quandoSugestaoExiste() {
        // ARRANGE
        var sugestao = new SugestaoConteudo();

        when(sugestaoConteudoRepository.buscarPorId(1L)).thenReturn(Optional.of(sugestao));

        // ACT
        sugestaoConteudoService.deletar(1L);

        // ASSERT
        verify(sugestaoConteudoRepository).deletarPorId(1L);
    }

    @Test
    void deveLancarNaoEncontrado_quandoDeletarSugestaoInexistente() {
        // ARRANGE
        when(sugestaoConteudoRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThatThrownBy(() -> sugestaoConteudoService.deletar(99L))
                .isInstanceOf(NaoEncontradoException.class)
                .hasMessage("Sugestão de conteúdo não encontrada.");

        verify(sugestaoConteudoRepository, never()).deletarPorId(any());
    }
}
