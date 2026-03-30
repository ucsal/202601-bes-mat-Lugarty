package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Participante;

public interface ParticipanteRepository extends ReadOnlyRepository<Participante, Long>, WriteRepository<Participante, Long> {
}