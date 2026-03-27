package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Participante;
import java.util.List;

public interface ParticipanteRepository {
    Participante save(Participante participante);
    Participante findById(Long id);
    List<Participante> findAll();
}