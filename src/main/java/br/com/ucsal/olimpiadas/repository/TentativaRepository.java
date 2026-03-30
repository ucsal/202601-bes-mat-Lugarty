package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Tentativa;

public interface TentativaRepository extends ReadOnlyRepository<Tentativa, Long>, WriteRepository<Tentativa, Long> {
}