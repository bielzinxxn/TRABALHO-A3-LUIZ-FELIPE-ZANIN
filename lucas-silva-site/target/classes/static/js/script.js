/* =================================================================
   LUCAS SILVA OFFICIAL — JavaScript principal
   - Menu mobile toggle
   - Header dinâmico no scroll
   - Animações de aparição em scroll
   - Gráficos (Chart.js) na página de estatísticas
   - Filtros da galeria
   - Validação do formulário de contato
   ================================================================= */

document.addEventListener('DOMContentLoaded', () => {

    /* --------- Menu mobile (hambúrguer) --------- */
    const toggle = document.querySelector('.nav-toggle');
    const menu = document.querySelector('.nav-menu');
    if (toggle && menu) {
        toggle.addEventListener('click', () => menu.classList.toggle('open'));
        // Fecha o menu ao clicar em um link
        menu.querySelectorAll('a').forEach(a =>
            a.addEventListener('click', () => menu.classList.remove('open')));
    }

    /* --------- Header muda de fundo no scroll --------- */
    const header = document.querySelector('.header');
    window.addEventListener('scroll', () => {
        header.classList.toggle('scrolled', window.scrollY > 50);
    });

    /* --------- Marca link ativo no menu --------- */
    const path = window.location.pathname;
    document.querySelectorAll('.nav-menu a').forEach(link => {
        if (link.getAttribute('href') === path) link.classList.add('active');
    });

    /* --------- Animações de aparição (IntersectionObserver) --------- */
    const reveals = document.querySelectorAll('.reveal');
    if ('IntersectionObserver' in window) {
        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.15 });
        reveals.forEach(el => observer.observe(el));
    } else {
        reveals.forEach(el => el.classList.add('visible'));
    }

    /* --------- Counter animado nos números da Home --------- */
    document.querySelectorAll('[data-counter]').forEach(el => {
        const final = parseInt(el.dataset.counter, 10);
        const duration = 1800;
        const start = performance.now();
        const animate = (now) => {
            const progress = Math.min((now - start) / duration, 1);
            el.textContent = Math.floor(progress * final);
            if (progress < 1) requestAnimationFrame(animate);
        };
        // Inicia o contador quando o elemento entra na tela
        const io = new IntersectionObserver((entries, obs) => {
            if (entries[0].isIntersecting) {
                requestAnimationFrame(animate);
                obs.disconnect();
            }
        });
        io.observe(el);
    });

    /* --------- Filtros da Galeria --------- */
    const tabs = document.querySelectorAll('.gallery-tab');
    const items = document.querySelectorAll('.gallery-item');
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            const filter = tab.dataset.filter;
            items.forEach(item => {
                const show = filter === 'all' || item.dataset.category === filter;
                item.style.display = show ? '' : 'none';
            });
        });
    });

    /* --------- Validação client-side do formulário de contato --------- */
    const form = document.querySelector('#form-contato');
    if (form) {
        form.addEventListener('submit', (e) => {
            let valido = true;

            // limpa erros antigos
            form.querySelectorAll('.form-error').forEach(s => s.remove());

            const nome = form.nome.value.trim();
            const email = form.email.value.trim();
            const msg = form.mensagem.value.trim();
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (nome.length < 3) {
                exibirErro(form.nome, 'Informe um nome com ao menos 3 caracteres.');
                valido = false;
            }
            if (!emailRegex.test(email)) {
                exibirErro(form.email, 'Informe um e-mail válido.');
                valido = false;
            }
            if (msg.length < 10) {
                exibirErro(form.mensagem, 'A mensagem precisa ter ao menos 10 caracteres.');
                valido = false;
            }
            if (!valido) e.preventDefault();
        });
    }

    function exibirErro(input, texto) {
        const small = document.createElement('small');
        small.className = 'form-error';
        small.textContent = texto;
        input.parentNode.appendChild(small);
        input.focus();
    }

    /* --------- Gráficos (Chart.js) na página de estatísticas --------- */
    if (typeof Chart !== 'undefined' && document.getElementById('chartGols')) {
        renderizarGraficos();
    }
});

/**
 * Renderiza os gráficos de gols/assistências e cartões usando Chart.js.
 * Os dados vêm de atributos data-* injetados pelo Thymeleaf.
 */
function renderizarGraficos() {
    // Os dados vêm de um objeto global injetado pelo Thymeleaf no template
    const d = window.__statsData || {};
    const temporadas = d.temporadas || [];
    const gols = d.gols || [];
    const assist = d.assistencias || [];
    const jogos = d.jogos || [];
    const amarelos = d.amarelos || [];
    const vermelhos = d.vermelhos || [];

    // Estilo padrão Chart.js — paleta dourada
    Chart.defaults.color = '#B8B8B8';
    Chart.defaults.borderColor = 'rgba(212,175,55,0.1)';
    Chart.defaults.font.family = 'Montserrat, sans-serif';

    // 1) Gols x Assistências por temporada (barras)
    new Chart(document.getElementById('chartGols'), {
        type: 'bar',
        data: {
            labels: temporadas,
            datasets: [
                {
                    label: 'Gols',
                    data: gols,
                    backgroundColor: 'rgba(212,175,55,0.85)',
                    borderColor: '#D4AF37',
                    borderWidth: 1
                },
                {
                    label: 'Assistências',
                    data: assist,
                    backgroundColor: 'rgba(255,255,255,0.7)',
                    borderColor: '#FFFFFF',
                    borderWidth: 1
                }
            ]
        },
        options: {
            responsive: true,
            plugins: { legend: { position: 'top' } },
            scales: {
                y: { beginAtZero: true, grid: { color: 'rgba(255,255,255,0.05)' } },
                x: { grid: { display: false } }
            }
        }
    });

    // 2) Evolução de jogos disputados (linha)
    new Chart(document.getElementById('chartJogos'), {
        type: 'line',
        data: {
            labels: temporadas,
            datasets: [{
                label: 'Jogos disputados',
                data: jogos,
                borderColor: '#D4AF37',
                backgroundColor: 'rgba(212,175,55,0.15)',
                tension: 0.35,
                fill: true,
                pointBackgroundColor: '#D4AF37',
                pointRadius: 5
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: { beginAtZero: true, grid: { color: 'rgba(255,255,255,0.05)' } },
                x: { grid: { display: false } }
            }
        }
    });

    // 3) Distribuição de cartões (donut)
    new Chart(document.getElementById('chartCartoes'), {
        type: 'doughnut',
        data: {
            labels: ['Amarelos', 'Vermelhos'],
            datasets: [{
                data: [
                    amarelos.reduce((a, b) => a + b, 0),
                    vermelhos.reduce((a, b) => a + b, 0)
                ],
                backgroundColor: ['#D4AF37', '#8B0000'],
                borderColor: '#0A0A0A',
                borderWidth: 3
            }]
        },
        options: {
            responsive: true,
            cutout: '65%',
            plugins: { legend: { position: 'bottom' } }
        }
    });
}
