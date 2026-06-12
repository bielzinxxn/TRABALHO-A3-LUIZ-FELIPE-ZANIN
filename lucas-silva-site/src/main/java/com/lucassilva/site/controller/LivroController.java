package com.lucassilva.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsável pela página do produto (livro do Lucas Silva)
 * e pelas telas de checkout simulado.
 *
 * IMPORTANTE: Todo o fluxo de pagamento é SIMULADO para fins acadêmicos.
 * Não há integração real com gateway de pagamento, banco ou processadora.
 */
@Controller
@RequestMapping("/livro")
public class LivroController {

    /** Página do produto: capa, sinopse, preço, avaliações e botão de comprar. */
    @GetMapping
    public String produto() {
        return "livro";
    }

    /** Tela de seleção do método de pagamento (PIX, crédito ou débito). */
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    /** Tela de pagamento via PIX (QR Code fake + contagem regressiva). */
    @GetMapping("/pagamento/pix")
    public String pix() {
        return "pagamento-pix";
    }

    /** Tela de pagamento via cartão de crédito (com parcelamento). */
    @GetMapping("/pagamento/credito")
    public String credito() {
        return "pagamento-cartao";
    }

    /** Tela de pagamento via cartão de débito (sem parcelamento). */
    @GetMapping("/pagamento/debito")
    public String debito() {
        return "pagamento-cartao-debito";
    }

    /** Tela final de confirmação com número de pedido gerado aleatoriamente. */
    @GetMapping("/confirmacao")
    public String confirmacao() {
        return "confirmacao";
    }
}
