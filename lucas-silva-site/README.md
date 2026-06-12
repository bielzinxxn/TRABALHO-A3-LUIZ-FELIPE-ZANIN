# LUCAS SILVA OFFICIAL — Portfólio Digital do Atleta

Site institucional desenvolvido em **Java + Spring Boot** para o atleta fictício **Lucas Silva**, atacante profissional de futebol. O projeto apresenta sua trajetória, estatísticas, galeria de fotos, notícias e canal de contato em um layout moderno e totalmente responsivo.

> Trabalho acadêmico — paleta preto / dourado / branco · arquitetura MVC · banco MySQL

---

## 🧰 Tecnologias utilizadas

| Camada | Tecnologia |
|---|---|
| Back-end | Java 17 · Spring Boot 3.2 |
| ORM / Persistência | Spring Data JPA · Hibernate |
| Banco de dados | MySQL 8 |
| Front-end | HTML5 · CSS3 · JavaScript (ES6+) |
| Template engine | Thymeleaf |
| Gráficos | Chart.js |
| Validação | Bean Validation (Jakarta) |
| Build | Maven |

---

## 📂 Estrutura do projeto

```
lucas-silva-site/
├── pom.xml                          ← dependências Maven
├── database/
│   └── schema.sql                   ← script SQL do banco MySQL
└── src/main/
    ├── java/com/lucassilva/site/
    │   ├── LucasSilvaApplication.java   ← classe principal
    │   ├── config/
    │   │   └── DataSeeder.java          ← popula dados iniciais
    │   ├── controller/
    │   │   ├── HomeController.java      ← roteamento das páginas
    │   │   ├── NoticiaController.java   ← CRUD de notícias
    │   │   └── ContatoController.java   ← formulário de contato
    │   ├── model/                       ← entidades JPA
    │   │   ├── Noticia.java
    │   │   ├── Estatistica.java
    │   │   └── MensagemContato.java
    │   ├── repository/                  ← repositórios Spring Data
    │   └── service/                     ← camada de regras de negócio
    └── resources/
        ├── application.properties       ← config do Spring + MySQL
        ├── static/
        │   ├── css/style.css            ← estilos completos
        │   ├── js/script.js             ← interações e gráficos
        │   └── images/                  ← (coloque aqui suas fotos)
        └── templates/                   ← páginas Thymeleaf
            ├── index.html
            ├── sobre.html
            ├── estatisticas.html
            ├── galeria.html
            ├── noticias.html
            ├── noticia-detalhe.html
            ├── noticia-form.html
            └── contato.html
```

---

## ⚙️ Como executar

### 1. Pré-requisitos
- Java 17 ou superior (`java -version`)
- Maven 3.8+ (`mvn -version`)
- MySQL 8+ rodando na porta padrão `3306`

### 2. Configuração do banco

Crie o banco (ou deixe o próprio Spring criar — a propriedade `createDatabaseIfNotExist=true` já faz isso):
```sql
CREATE DATABASE lucassilva_db DEFAULT CHARACTER SET utf8mb4;
```

Ajuste usuário e senha em **`src/main/resources/application.properties`**:
```properties
spring.datasource.username=root
spring.datasource.password=root
```

### 3. Rodar o projeto
Na raiz do projeto:
```bash
mvn spring-boot:run
```

Aguarde a mensagem `Started LucasSilvaApplication`.
Acesse no navegador: **http://localhost:8080**

---

## 🌐 Rotas / Páginas

| URL | Descrição |
|---|---|
| `/` | Home — destaque, números da carreira e notícias |
| `/sobre` | Biografia, trajetória e conquistas |
| `/estatisticas` | Cards + gráficos interativos + tabela detalhada |
| `/galeria` | Fotos de partidas, treinos e comemorações (filtráveis) |
| `/noticias` | Listagem de notícias |
| `/noticias/{id}` | Detalhe de uma notícia |
| `/noticias/nova` | Formulário para cadastrar nova notícia |
| `/contato` | Formulário de contato com validação |

---

## ✅ Recursos implementados

- ✅ Menu de navegação fixo com efeito blur ao rolar
- ✅ Hero section com nome, posição, clube, frase motivacional e redes sociais
- ✅ Biografia completa, história de carreira e lista de conquistas
- ✅ Estatísticas: cards, **três gráficos Chart.js** e tabela histórica
- ✅ Galeria com **filtro por categoria** (Partidas / Treinos / Comemorações)
- ✅ CRUD de notícias com banco MySQL
- ✅ Formulário de contato com validação **client-side** (JS) e **server-side** (Bean Validation)
- ✅ Layout responsivo (mobile, tablet, desktop) com menu hambúrguer
- ✅ Animações suaves (reveal on scroll, contadores, transições)
- ✅ Integração com Instagram, X (Twitter), Facebook e YouTube
- ✅ SEO básico: meta tags, Open Graph, HTML semântico
- ✅ Arquitetura **MVC** com separação clara: Controller → Service → Repository → Model
- ✅ Código totalmente comentado em português

---

## 🖼️ Como trocar as imagens placeholder pelas fotos reais

O site usa placeholders estilizados (a sigla "LS" e blocos dourados) onde devem entrar as fotos reais. Para substituir:

1. Coloque os arquivos `.jpg` / `.png` em `src/main/resources/static/images/`.
2. Nos templates, troque os blocos `<div class="hero-image-placeholder">10</div>` por:
   ```html
   <img th:src="@{/images/lucas-foto-principal.jpg}" alt="Lucas Silva">
   ```
3. O mesmo vale para `bio-image`, `gallery-placeholder` e `news-image`.

---

## 👥 Integrantes do grupo
Preencher na apresentação final.

---

## 📄 Licença
Projeto acadêmico — uso educacional. Todas as marcas de clubes e nomes citados são fictícios ou usados apenas para fins ilustrativos.
