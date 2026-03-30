package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Questao;

public interface QuestaoRepository extends ReadOnlyRepository<Questao, Long>, WriteRepository<Questao, Long> {
}