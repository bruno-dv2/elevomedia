package com.elevomedia.elevomedia_backend.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ClienteResponseDTO(

        Long id,
        Long usuarioId,
        String nomeNegocio,
        String segmento,
        String cidade,
        String estado,
        String oQueVende,
        String diferenciais,
        String horarioFuncionamento,
        String promocoesFixas,
        String redesSociais,
        String site,
        Integer quantidadePostsMes,
        String publicoFaixaEtaria,
        String publicoGenero,
        String publicoClasseSocial,
        String tomDeVoz,
        Boolean usaHumor,
        String coresPrincipais,
        String estiloVisual,
        String observacoesLivres,
        Boolean ativo,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime criadoEm,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime atualizadoEm
) {}
