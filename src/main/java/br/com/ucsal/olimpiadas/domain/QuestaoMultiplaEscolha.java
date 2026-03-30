package br.com.ucsal.olimpiadas.domain;

public class QuestaoMultiplaEscolha implements Questao {
    private Long id;
    private Long provaId;
    private String enunciado;
    private String[] alternativas;
    private char alternativaCorreta;
    private String fenInicial;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getProvaId() {
        return provaId;
    }

    public void setProvaId(Long provaId) {
        this.provaId = provaId;
    }

    @Override
    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }

    public char getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public void setAlternativaCorreta(char alternativaCorreta) {
        this.alternativaCorreta = alternativaCorreta;
    }

    public String getFenInicial() {
        return fenInicial;
    }

    public void setFenInicial(String fenInicial) {
        this.fenInicial = fenInicial;
    }

    @Override
    public boolean isRespostaCorreta(char marcada) {
        return marcada == alternativaCorreta;
    }

    public static char normalizar(char c) {
        return Character.toUpperCase(c);
    }
}