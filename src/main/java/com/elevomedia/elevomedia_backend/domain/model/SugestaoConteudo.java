package com.elevomedia.elevomedia_backend.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sugestao_conteudo")
public class SugestaoConteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planejamento_id", nullable = false)
    private Planejamento planejamento;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name = "titulo_data")
    private String tituloData;

    private String legenda;

    private String hashtags;

    @Column(name = "chamada_acao")
    private String chamadaAcao;

    @Column(name = "roteiro_video")
    private String roteiroVideo;

    @Column(nullable = false)
    private String status = "SUGERIDO";

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    public void atualizarCom(SugestaoConteudo dados) {
        if (dados.getTituloData() != null) this.tituloData = dados.getTituloData();
        if (dados.getLegenda() != null) this.legenda = dados.getLegenda();
        if (dados.getHashtags() != null) this.hashtags = dados.getHashtags();
        if (dados.getChamadaAcao() != null) this.chamadaAcao = dados.getChamadaAcao();
        if (dados.getRoteiroVideo() != null) this.roteiroVideo = dados.getRoteiroVideo();
        if (dados.getDataPublicacao() != null) this.dataPublicacao = dados.getDataPublicacao();
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
