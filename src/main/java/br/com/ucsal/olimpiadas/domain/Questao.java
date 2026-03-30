package br.com.ucsal.olimpiadas.domain;

public interface Questao {
    Long getId();
    Long getProvaId();
    String getEnunciado();
    boolean isRespostaCorreta(char marcada);
}