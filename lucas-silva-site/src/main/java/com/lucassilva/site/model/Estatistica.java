package com.lucassilva.site.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA que representa as estatísticas do jogador por temporada.
 * Mapeada para a tabela "estatisticas" no MySQL.
 */
@Entity
@Table(name = "estatisticas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estatistica {

    /** Chave primária autoincrementada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Temporada à qual a estatística se refere (ex.: "2024/2025"). */
    @Column(nullable = false, length = 20)
    private String temporada;

    /** Clube em que o atleta atuou na temporada. */
    @Column(length = 80)
    private String clube;

    /** Total de jogos disputados. */
    @Column(nullable = false)
    private Integer jogos = 0;

    /** Total de gols marcados. */
    @Column(nullable = false)
    private Integer gols = 0;

    /** Total de assistências. */
    @Column(nullable = false)
    private Integer assistencias = 0;

    /** Total de cartões amarelos recebidos. */
    @Column(name = "cartoes_amarelos", nullable = false)
    private Integer cartoesAmarelos = 0;

    /** Total de cartões vermelhos recebidos. */
    @Column(name = "cartoes_vermelhos", nullable = false)
    private Integer cartoesVermelhos = 0;
}
