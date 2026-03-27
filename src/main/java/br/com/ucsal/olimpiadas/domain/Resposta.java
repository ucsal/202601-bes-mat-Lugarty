package br.com.ucsal.olimpiadas.domain;

public class Resposta {
    private Long questaoId;
    private char alternativaMarcada;
    private boolean correta;

    public Long getQuestaoId() {
        return questaoId;
    }

    public void setQuestaoId(Long questaoId) {
        this.questaoId = questaoId;
    }

    public char getAlternativaMarcada() {
        return alternativaMarcada;
    }

    public void setAlternativaMarcada(char alternativaMarcada) {
        this.alternativaMarcada = alternativaMarcada;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }
}