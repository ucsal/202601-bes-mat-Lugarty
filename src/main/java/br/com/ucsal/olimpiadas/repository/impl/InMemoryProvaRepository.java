package br.com.ucsal.olimpiadas.repository.impl;

import br.com.ucsal.olimpiadas.domain.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import java.util.*;

public class InMemoryProvaRepository implements ProvaRepository {
    private final Map<Long, Prova> store = new HashMap<>();
    private long currentId = 1;

    @Override
    public Prova save(Prova prova) {
        if (prova.getId() == null) {
            prova.setId(currentId++);
        }
        store.put(prova.getId(), prova);
        return prova;
    }

    @Override
    public Prova findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Prova> findAll() {
        return new ArrayList<>(store.values());
    }
}