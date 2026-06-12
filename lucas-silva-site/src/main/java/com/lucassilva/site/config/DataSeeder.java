package com.lucassilva.site.config;

import com.lucassilva.site.model.Estatistica;
import com.lucassilva.site.model.Noticia;
import com.lucassilva.site.repository.EstatisticaRepository;
import com.lucassilva.site.repository.NoticiaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Popula o banco de dados com dados de exemplo na primeira execução.
 * Útil para demonstração em sala de aula sem precisar cadastrar tudo manualmente.
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner inicializar(EstatisticaRepository estatisticaRepo,
                                  NoticiaRepository noticiaRepo) {
        return args -> {

            // ----- Estatísticas (uma linha por temporada) -----
            if (estatisticaRepo.count() == 0) {
                estatisticaRepo.save(new Estatistica(null, "2020/2021", "Santos FC", 32, 11, 7, 4, 0));
                estatisticaRepo.save(new Estatistica(null, "2021/2022", "Santos FC", 38, 18, 9, 5, 1));
                estatisticaRepo.save(new Estatistica(null, "2022/2023", "SE Palmeiras", 41, 23, 12, 3, 0));
                estatisticaRepo.save(new Estatistica(null, "2023/2024", "SE Palmeiras", 45, 27, 14, 6, 0));
                estatisticaRepo.save(new Estatistica(null, "2024/2025", "SE Palmeiras", 39, 24, 11, 4, 1));
            }

            // ----- Notícias de exemplo -----
            if (noticiaRepo.count() == 0) {
                Noticia n1 = new Noticia();
                n1.setTitulo("Lucas Silva marca hat-trick na vitória do Palmeiras");
                n1.setResumo("Atacante brilha em noite histórica no Allianz Parque.");
                n1.setConteudo("Em uma noite memorável no Allianz Parque, Lucas Silva foi o grande nome da partida ao marcar três gols na vitória por 4 a 1. O atacante, que vive a melhor fase da carreira, já soma 24 gols na temporada e lidera a artilharia do campeonato.");
                n1.setImagem("/images/noticia1.jpg");
                noticiaRepo.save(n1);

                Noticia n2 = new Noticia();
                n2.setTitulo("Convocação para a Seleção Brasileira");
                n2.setResumo("Lucas é chamado para os próximos jogos das Eliminatórias.");
                n2.setConteudo("O técnico da Seleção Brasileira anunciou nesta sexta-feira a convocação de Lucas Silva para os próximos compromissos das Eliminatórias da Copa do Mundo. É a terceira convocação consecutiva do atacante.");
                n2.setImagem("/images/noticia2.jpg");
                noticiaRepo.save(n2);

                Noticia n3 = new Noticia();
                n3.setTitulo("Renovação de contrato até 2028");
                n3.setResumo("Atacante renova vínculo com o clube alviverde.");
                n3.setConteudo("A diretoria do Palmeiras oficializou hoje a renovação contratual de Lucas Silva até dezembro de 2028. O novo vínculo inclui aumento salarial e cláusula de rescisão recorde para o clube.");
                n3.setImagem("/images/noticia3.jpg");
                noticiaRepo.save(n3);
            }
        };
    }
}
