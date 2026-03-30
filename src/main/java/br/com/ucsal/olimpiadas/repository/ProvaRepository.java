package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Prova;

public interface ProvaRepository extends ReadOnlyRepository<Prova, Long>, WriteRepository<Prova, Long> {
}