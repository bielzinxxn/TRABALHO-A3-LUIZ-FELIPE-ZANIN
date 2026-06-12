package com.lucassilva.site.repository;

import com.lucassilva.site.model.MensagemContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para mensagens enviadas pelo formulário de contato.
 */
@Repository
public interface MensagemContatoRepository extends JpaRepository<MensagemContato, Long> {
}
