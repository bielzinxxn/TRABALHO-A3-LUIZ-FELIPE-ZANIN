package com.lucassilva.site.service;

import com.lucassilva.site.model.Noticia;
import com.lucassilva.site.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço com as regras de negócio relacionadas a notícias.
 * Centraliza o acesso ao repositório e isola os controllers da camada de persistência.
 */
@Service
public class NoticiaService {

    private final NoticiaRepository repository;

    @Autowired
    public NoticiaService(NoticiaRepository repository) {
        this.repository = repository;
    }

    /** Lista todas as notícias ordenadas da mais recente para a mais antiga. */
    public List<Noticia> listarTodas() {
        return repository.findAllByOrderByDataPublicacaoDesc();
    }

    /** Busca uma notícia específica pelo seu ID. */
    public Optional<Noticia> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /** Cadastra uma nova notícia (a data é preenchida automaticamente). */
    public Noticia salvar(Noticia noticia) {
        return repository.save(noticia);
    }

    /** Remove uma notícia pelo ID. */
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
