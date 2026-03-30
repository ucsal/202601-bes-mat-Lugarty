package br.com.ucsal.olimpiadas.repository.impl;

import br.com.ucsal.olimpiadas.domain.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import java.util.*;

public class InMemoryParticipanteRepository implements ParticipanteRepository {
    private final Map<Long, Participante> store = new HashMap<>();
    private long currentId = 1;

    @Override
    public Participante save(Participante participante) {
        if (participante.getId() == null) {
            participante.setId(currentId++);
        }
        store.put(participante.getId(), participante);
        return participante;
    }

    @Override
    public Participante findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Participante> findAll() {
        return new ArrayList<>(store.values());
    }
}