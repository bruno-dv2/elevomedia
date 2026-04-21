package com.elevomedia.elevomedia_backend.presentation.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank
        @Size(max = 150)
        String nomeNegocio,

        @NotBlank
        @Size(max = 100)
        String segmento,

        String cidade,

        @Size(min = 2, max = 2)
        String estado,

        String oQueVende,
        String diferenciais,
        String horarioFuncionamento,
        String promocoesFixas,
        String redesSociais,

        @Size(max = 300)
        String site,

        @NotNull
        @Min(1) @Max(120)
        Integer quantidadePostsMes,

        String publicoFaixaEtaria,
        String publicoGenero,
        String publicoClasseSocial,
        String tomDeVoz,
        Boolean usaHumor,
        String coresPrincipais,
        String estiloVisual,
        String observacoesLivres
) {}
