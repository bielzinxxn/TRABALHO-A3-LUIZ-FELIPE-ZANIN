package com.lucassilva.site.service;

import com.lucassilva.site.model.Estatistica;
import com.lucassilva.site.repository.EstatisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço com as regras de negócio relacionadas às estatísticas do atleta.
 */
@Service
public class EstatisticaService {

    private final EstatisticaRepository repository;

    @Autowired
    public EstatisticaService(EstatisticaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as estatísticas em ordem cronológica. */
    public List<Estatistica> listarTodas() {
        return repository.findAllByOrderByTemporadaAsc();
    }

    /** Cadastra ou atualiza uma estatística. */
    public Estatistica salvar(Estatistica estatistica) {
        return repository.save(estatistica);
    }

    /**
     * Calcula totais agregados de todas as temporadas.
     * Retorna um array no formato [jogos, gols, assistências, amarelos, vermelhos].
     */
    public int[] calcularTotais() {
        List<Estatistica> lista = listarTodas();
        int[] totais = new int[5]; // {jogos, gols, assist, amarelos, vermelhos}
        for (Estatistica e : lista) {
            totais[0] += e.getJogos();
            totais[1] += e.getGols();
            totais[2] += e.getAssistencias();
            totais[3] += e.getCartoesAmarelos();
            totais[4] += e.getCartoesVermelhos();
        }
        return totais;
    }
}
