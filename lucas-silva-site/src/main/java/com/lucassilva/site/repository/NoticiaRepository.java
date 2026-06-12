package com.lucassilva.site.repository;

import com.lucassilva.site.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Notícia.
 * Herda métodos prontos como findAll(), save(), deleteById() etc.
 */
@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    /**
     * Busca notícias ordenadas da mais recente para a mais antiga.
     * Spring Data infere a query a partir do nome do método.
     */
    List<Noticia> findAllByOrderByDataPublicacaoDesc();
}
