package com.lucassilva.site.controller;

import com.lucassilva.site.model.MensagemContato;
import com.lucassilva.site.service.EstatisticaService;
import com.lucassilva.site.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller principal: renderiza as páginas estáticas do site.
 * Cada método retorna o NOME do template Thymeleaf (sem a extensão .html).
 */
@Controller
public class HomeController {

    private final NoticiaService noticiaService;
    private final EstatisticaService estatisticaService;

    @Autowired
    public HomeController(NoticiaService noticiaService, EstatisticaService estatisticaService) {
        this.noticiaService = noticiaService;
        this.estatisticaService = estatisticaService;
    }

    /** Página inicial (Home). */
    @GetMapping("/")
    public String home(Model model) {
        // Exibe as 3 notícias mais recentes em destaque na Home
        model.addAttribute("noticiasDestaque",
                noticiaService.listarTodas().stream().limit(3).toList());
        // Totais agregados para a seção "Números da Carreira"
        int[] totais = estatisticaService.calcularTotais();
        model.addAttribute("totalJogos", totais[0]);
        model.addAttribute("totalGols", totais[1]);
        model.addAttribute("totalAssistencias", totais[2]);
        return "index";
    }

    /** Página "Sobre" com biografia. */
    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    /** Página de estatísticas com gráficos. */
    @GetMapping("/estatisticas")
    public String estatisticas(Model model) {
        model.addAttribute("estatisticas", estatisticaService.listarTodas());
        int[] totais = estatisticaService.calcularTotais();
        model.addAttribute("totalJogos", totais[0]);
        model.addAttribute("totalGols", totais[1]);
        model.addAttribute("totalAssistencias", totais[2]);
        model.addAttribute("totalAmarelos", totais[3]);
        model.addAttribute("totalVermelhos", totais[4]);
        return "estatisticas";
    }

    /** Galeria de fotos. */
    @GetMapping("/galeria")
    public String galeria() {
        return "galeria";
    }

    /** Página de contato (formulário). */
    @GetMapping("/contato")
    public String contato(Model model) {
        // Objeto vazio para o th:object do formulário Thymeleaf
        model.addAttribute("mensagem", new MensagemContato());
        return "contato";
    }
}
