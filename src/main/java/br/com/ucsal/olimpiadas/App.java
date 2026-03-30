package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.domain.*;
import br.com.ucsal.olimpiadas.repository.*;
import br.com.ucsal.olimpiadas.repository.impl.*;
import br.com.ucsal.olimpiadas.service.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final ParticipanteService participanteService;
    private final ProvaService provaService;
    private final QuestaoService questaoService;
    private final TentativaService tentativaService;
    private final Scanner in;

    public App(ParticipanteService participanteService,
               ProvaService provaService,
               QuestaoService questaoService,
               TentativaService tentativaService) {
        this.participanteService = participanteService;
        this.provaService = provaService;
        this.questaoService = questaoService;
        this.tentativaService = tentativaService;
        this.in = new Scanner(System.in);
    }

    public void start() {
        seed();

        while (true) {
            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão (A–E) em uma prova");
            System.out.println("4) Aplicar prova (selecionar participante + prova)");
            System.out.println("5) Listar tentativas (resumo)");
            System.out.println("0) Sair");
            System.out.print("> ");

            switch (in.nextLine()) {
                case "1" -> cadastrarParticipante();
                case "2" -> cadastrarProva();
                case "3" -> cadastrarQuestao();
                case "4" -> aplicarProva();
                case "5" -> listarTentativas();
                case "0" -> {
                    System.out.println("tchau");
                    return;
                }
                default -> System.out.println("opção inválida");
            }
        }
    }

    private void cadastrarParticipante() {
        System.out.print("Nome: ");
        var nome = in.nextLine();

        System.out.print("Email (opcional): ");
        var email = in.nextLine();

        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        var p = participanteService.cadastrar(nome, email);
        System.out.println("Participante cadastrado: " + p.getId());
    }

    private void cadastrarProva() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        var prova = provaService.criar(titulo);
        System.out.println("Prova criada: " + prova.getId());
    }

    private void cadastrarQuestao() {
        var provas = provaService.listarTodas();
        if (provas.isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        var provaId = escolherProva();
        if (provaId == null) return;

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = QuestaoMultiplaEscolha.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var q = questaoService.adicionarQuestao(provaId, enunciado, alternativas, correta);
        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }

    private void aplicarProva() {
        var participantes = participanteService.listarTodos();
        var provas = provaService.listarTodas();

        if (participantes.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (provas.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = escolherParticipante();
        if (participanteId == null) return;

        var provaId = escolherProva();
        if (provaId == null) return;

        var questoesDaProva = questaoService.listarQuestoesPorProva(provaId);
        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        System.out.println("\n--- Início da Prova ---");
        List<Resposta> respostas = new ArrayList<>();

        for (var q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            // Apenas para questões do tipo múltipla escolha, exibimos alternativas e tabuleiro
            if (q instanceof QuestaoMultiplaEscolha) {
                QuestaoMultiplaEscolha qme = (QuestaoMultiplaEscolha) q;
                System.out.println("Posição inicial:");
                imprimirTabuleiroFen(qme.getFenInicial());

                for (var alt : qme.getAlternativas()) {
                    System.out.println(alt);
                }
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = QuestaoMultiplaEscolha.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            var r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));
            respostas.add(r);
        }

        var tentativa = tentativaService.iniciarTentativa(participanteId, provaId, respostas);
        int nota = tentativaService.calcularNota(tentativa);

        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }

    private void listarTentativas() {
        System.out.println("\n--- Tentativas ---");
        for (var t : tentativaService.listarTodas()) {
            int nota = tentativaService.calcularNota(t);
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
                    t.getId(), t.getParticipanteId(), t.getProvaId(),
                    nota, t.getRespostas().size());
        }
    }

    private Long escolherParticipante() {
        var participantes = participanteService.listarTodos();
        System.out.println("\nParticipantes:");
        for (var p : participantes) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = participantes.stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }

    private Long escolherProva() {
        var provas = provaService.listarTodas();
        System.out.println("\nProvas:");
        for (var p : provas) {
            System.out.printf("  %d) %s%n", p.getId(), p.getTitulo());
        }
        System.out.print("Escolha o id da prova: ");

        try {
            long id = Long.parseLong(in.nextLine());
            boolean existe = provas.stream().anyMatch(p -> p.getId() == id);
            if (!existe) {
                System.out.println("id inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("entrada inválida");
            return null;
        }
    }

    private void imprimirTabuleiroFen(String fen) {
        String parteTabuleiro = fen.split(" ")[0];
        String[] ranks = parteTabuleiro.split("/");

        System.out.println();
        System.out.println("    a b c d e f g h");
        System.out.println("   -----------------");

        for (int r = 0; r < 8; r++) {
            String rank = ranks[r];
            System.out.print((8 - r) + " | ");

            for (char c : rank.toCharArray()) {
                if (Character.isDigit(c)) {
                    int vazios = c - '0';
                    for (int i = 0; i < vazios; i++) {
                        System.out.print(". ");
                    }
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println("| " + (8 - r));
        }

        System.out.println("   -----------------");
        System.out.println("    a b c d e f g h");
        System.out.println();
    }

    private void seed() {
        var prova = provaService.criar("Olimpíada 2026 • Nível 1 • Prova A");

        var q = new QuestaoMultiplaEscolha();
        q.setProvaId(prova.getId());
        q.setEnunciado("""
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """);
        q.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");
        q.setAlternativas(new String[]{"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"});
        q.setAlternativaCorreta('C');
        questaoService.adicionarQuestao(prova.getId(), q.getEnunciado(), q.getAlternativas(), q.getAlternativaCorreta());
    }

    public static void main(String[] args) {
        ParticipanteRepository participanteRepo = new InMemoryParticipanteRepository();
        ProvaRepository provaRepo = new InMemoryProvaRepository();
        QuestaoRepository questaoRepo = new InMemoryQuestaoRepository();
        TentativaRepository tentativaRepo = new InMemoryTentativaRepository();

        ParticipanteService participanteService = new ParticipanteService(participanteRepo);
        ProvaService provaService = new ProvaService(provaRepo);
        QuestaoService questaoService = new QuestaoService(questaoRepo);
        TentativaService tentativaService = new TentativaService(tentativaRepo, questaoRepo);

        App app = new App(participanteService, provaService, questaoService, tentativaService);
        app.start();
    }
}