package br.com.ucsal.olimpiadas.repository.impl;

import br.com.ucsal.olimpiadas.domain.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import java.util.*;

public class InMemoryTentativaRepository implements TentativaRepository {
    private final Map<Long, Tentativa> store = new HashMap<>();
    private long currentId = 1;

    @Override
    public Tentativa save(Tentativa tentativa) {
        if (tentativa.getId() == null) {
            tentativa.setId(currentId++);
        }
        store.put(tentativa.getId(), tentativa);
        return tentativa;
    }

    @Override
    public Tentativa findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Tentativa> findAll() {
        return new ArrayList<>(store.values());
    }
}