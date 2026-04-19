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

    public Cliente(Long id) {
        this.id = id;
    }

    public void atualizarCom(Cliente dados) {
        if (dados.getNomeNegocio() != null) this.nomeNegocio = dados.getNomeNegocio();
        if (dados.getSegmento() != null) this.segmento = dados.getSegmento();
        if (dados.getCidade() != null) this.cidade = dados.getCidade();
        if (dados.getEstado() != null) this.estado = dados.getEstado();
        if (dados.getOQueVende() != null) this.oQueVende = dados.getOQueVende();
        if (dados.getDiferenciais() != null) this.diferenciais = dados.getDiferenciais();
        if (dados.getHorarioFuncionamento() != null) this.horarioFuncionamento = dados.getHorarioFuncionamento();
        if (dados.getPromocoesFixas() != null) this.promocoesFixas = dados.getPromocoesFixas();
        if (dados.getRedesSociais() != null) this.redesSociais = dados.getRedesSociais();
        if (dados.getSite() != null) this.site = dados.getSite();
        if (dados.getQuantidadePostsMes() != null) this.quantidadePostsMes = dados.getQuantidadePostsMes();
        if (dados.getPublicoFaixaEtaria() != null) this.publicoFaixaEtaria = dados.getPublicoFaixaEtaria();
        if (dados.getPublicoGenero() != null) this.publicoGenero = dados.getPublicoGenero();
        if (dados.getPublicoClasseSocial() != null) this.publicoClasseSocial = dados.getPublicoClasseSocial();
        if (dados.getTomDeVoz() != null) this.tomDeVoz = dados.getTomDeVoz();
        if (dados.getUsaHumor() != null) this.usaHumor = dados.getUsaHumor();
        if (dados.getCoresPrincipais() != null) this.coresPrincipais = dados.getCoresPrincipais();
        if (dados.getEstiloVisual() != null) this.estiloVisual = dados.getEstiloVisual();
        if (dados.getObservacoesLivres() != null) this.observacoesLivres = dados.getObservacoesLivres();
    }

    @PrePersist
    private void prePersist() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        atualizadoEm = LocalDateTime.now();
    }
}
