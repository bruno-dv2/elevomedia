package com.elevomedia.elevomedia_backend.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "nome_negocio", nullable = false)
    private String nomeNegocio;

    @Column(nullable = false)
    private String segmento;

    private String cidade;

    private String estado;

    @Column(name = "o_que_vende")
    private String oQueVende;

    private String diferenciais;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "promocoes_fixas")
    private String promocoesFixas;

    @Column(name = "redes_sociais")
    private String redesSociais;

    private String site;

    @Column(name = "quantidade_posts_mes")
    private Integer quantidadePostsMes;

    @Column(name = "publico_faixa_etaria")
    private String publicoFaixaEtaria;

    @Column(name = "publico_genero")
    private String publicoGenero;

    @Column(name = "publico_classe_social")
    private String publicoClasseSocial;

    @Column(name = "tom_de_voz")
    private String tomDeVoz;

    @Column(name = "usa_humor")
    private Boolean usaHumor = false;

    @Column(name = "cores_principais")
    private String coresPrincipais;

    @Column(name = "estilo_visual")
    private String estiloVisual;

    @Column(name = "observacoes_livres")
    private String observacoesLivres;

    private Boolean ativo = true;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
