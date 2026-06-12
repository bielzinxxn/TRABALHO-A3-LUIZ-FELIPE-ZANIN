package com.lucassilva.site.service;

import com.lucassilva.site.model.MensagemContato;
import com.lucassilva.site.repository.MensagemContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pelas mensagens recebidas via formulário de contato.
 */
@Service
public class ContatoService {

    private final MensagemContatoRepository repository;

    @Autowired
    public ContatoService(MensagemContatoRepository repository) {
        this.repository = repository;
    }

    /** Persiste uma nova mensagem de contato no banco. */
    public MensagemContato salvar(MensagemContato mensagem) {
        return repository.save(mensagem);
    }

    /** Lista todas as mensagens recebidas (uso administrativo). */
    public List<MensagemContato> listarTodas() {
        return repository.findAll();
    }
}
