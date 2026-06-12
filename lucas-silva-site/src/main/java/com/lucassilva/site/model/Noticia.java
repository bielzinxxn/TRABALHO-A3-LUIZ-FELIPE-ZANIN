package com.lucassilva.site.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidade JPA que representa uma notícia publicada no site.
 * Mapeada para a tabela "noticias" no MySQL.
 */
@Entity
@Table(name = "noticias")
@Data                 // Lombok: gera getters, setters, toString, equals e hashCode
@NoArgsConstructor    // Construtor sem argumentos exigido pela JPA
@AllArgsConstructor   // Construtor com todos os campos (útil em testes)
public class Noticia {

    /** Chave primária autoincrementada. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Título principal da notícia. */
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String titulo;

    /** Resumo curto (chamada da notícia). */
    @Size(max = 300)
    @Column(length = 300)
    private String resumo;

    /** Conteúdo completo da notícia em texto/HTML simples. */
    @NotBlank(message = "O conteúdo é obrigatório")
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    /** Caminho/URL da imagem destacada (banner da notícia). */
    @Column(length = 255)
    private String imagem;

    /** Data e hora da publicação (preenchida automaticamente). */
    @Column(name = "data_publicacao", nullable = false, updatable = false)
    private LocalDateTime dataPublicacao;

    /**
     * Callback executado antes da inserção no banco.
     * Define automaticamente a data de publicação.
     */
    @PrePersist
    protected void aoCriar() {
        this.dataPublicacao = LocalDateTime.now();
    }
}
