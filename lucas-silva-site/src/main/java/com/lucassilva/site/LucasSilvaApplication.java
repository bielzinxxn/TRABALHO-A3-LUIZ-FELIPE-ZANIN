package com.lucassilva.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação.
 *
 * A anotação @SpringBootApplication habilita:
 *  - @Configuration  -> classe de configuração Spring
 *  - @EnableAutoConfiguration -> configuração automática do framework
 *  - @ComponentScan  -> varredura de componentes (Controllers, Services, etc.)
 */
@SpringBootApplication
public class LucasSilvaApplication {

    /**
     * Método de entrada (entry point) da aplicação.
     * Inicializa o servidor Tomcat embarcado e a infraestrutura Spring.
     */
    public static void main(String[] args) {
        SpringApplication.run(LucasSilvaApplication.class, args);
    }
}
