package com.lucassilva.site.controller;

import com.lucassilva.site.model.MensagemContato;
import com.lucassilva.site.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsável pelo recebimento das mensagens do formulário de contato.
 */
@Controller
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService service;

    @Autowired
    public ContatoController(ContatoService service) {
        this.service = service;
    }

    /**
     * Recebe o POST do formulário, valida e persiste a mensagem.
     * Em caso de erro de validação, retorna ao formulário com as mensagens.
     */
    @PostMapping
    public String enviar(@Valid @ModelAttribute("mensagem") MensagemContato mensagem,
                         BindingResult result,
                         RedirectAttributes flash) {
        if (result.hasErrors()) {
            // Volta ao template do formulário (mantém os campos preenchidos e exibe erros)
            return "contato";
        }
        service.salvar(mensagem);
        flash.addFlashAttribute("sucesso", "Mensagem enviada com sucesso! Em breve entraremos em contato.");
        return "redirect:/contato";
    }
}
