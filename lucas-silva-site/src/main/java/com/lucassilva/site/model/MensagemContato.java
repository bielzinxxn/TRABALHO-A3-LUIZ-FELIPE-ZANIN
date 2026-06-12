package com.lucassilva.site.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidade JPA que representa uma mensagem enviada pelo formulário de contato.
 * Mapeada para a tabela "mensagens_contato".
 */
@Entity
@Table(name = "mensagens_contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nome do remetente. */
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    /** E-mail do remetente (formato validado pela anotação @Email). */
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(nullable = false, length = 120)
    private String email;

    /** Texto da mensagem. */
    @NotBlank(message = "A mensagem é obrigatória")
    @Size(min = 10, max = 1000, message = "A mensagem deve ter entre 10 e 1000 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    /** Data e hora do envio (preenchida automaticamente). */
    @Column(name = "data_envio", nullable = false, updatable = false)
    private LocalDateTime dataEnvio;

    @PrePersist
    protected void aoCriar() {
        this.dataEnvio = LocalDateTime.now();
    }
}
