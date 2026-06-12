package com.lucassilva.site.repository;

import com.lucassilva.site.model.Estatistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para a entidade Estatística.
 */
@Repository
public interface EstatisticaRepository extends JpaRepository<Estatistica, Long> {

    /** Lista todas as temporadas em ordem cronológica crescente. */
    List<Estatistica> findAllByOrderByTemporadaAsc();
}
