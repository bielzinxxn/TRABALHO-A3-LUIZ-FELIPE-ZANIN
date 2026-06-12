package com.lucassilva.site.controller;

import com.lucassilva.site.model.Noticia;
import com.lucassilva.site.service.NoticiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller das notícias: listagem pública e cadastro/admin.
 */
@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService service;

    @Autowired
    public NoticiaController(NoticiaService service) {
        this.service = service;
    }

    /** Lista pública de notícias. */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("noticias", service.listarTodas());
        return "noticias";
    }

    /** Página de detalhe de uma notícia específica. */
    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        Noticia noticia = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
        model.addAttribute("noticia", noticia);
        return "noticia-detalhe";
    }

    /** Formulário simples para cadastro de novas notícias (área administrativa). */
    @GetMapping("/nova")
    public String formNova(Model model) {
        model.addAttribute("noticia", new Noticia());
        model.addAttribute("modoEdicao", false);
        return "noticia-form";
    }

    /** Processa o POST de cadastro. */
    @PostMapping
    public String salvar(@Valid @ModelAttribute("noticia") Noticia noticia,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "noticia-form";
        }
        service.salvar(noticia);
        return "redirect:/noticias";
    }

    /** Exibe o formulário preenchido com os dados atuais para edição. */
    @GetMapping("/{id}/editar")
    public String formEditar(@PathVariable Long id, Model model) {
        Noticia noticia = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
        model.addAttribute("noticia", noticia);
        model.addAttribute("modoEdicao", true);
        return "noticia-form";
    }

    /** Processa o POST da edição (mantém o mesmo ID e a data original). */
    @PostMapping("/{id}/editar")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("noticia") Noticia noticia,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            return "noticia-form";
        }
        // Recupera a notícia original para manter a data de publicação
        Noticia original = service.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
        noticia.setId(id);
        noticia.setDataPublicacao(original.getDataPublicacao());
        service.salvar(noticia);
        return "redirect:/noticias";
    }

    /** Remove uma notícia pelo ID. */
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        service.remover(id);
        return "redirect:/noticias";
    }
}
