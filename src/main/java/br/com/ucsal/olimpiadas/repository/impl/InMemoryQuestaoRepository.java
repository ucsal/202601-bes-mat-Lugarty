package br.com.ucsal.olimpiadas.repository.impl;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import java.util.*;

public class InMemoryQuestaoRepository implements QuestaoRepository {
    private final Map<Long, Questao> store = new HashMap<>();
    private long currentId = 1;

    @Override
    public Questao save(Questao questao) {
        if (questao.getId() == null) {
            if (questao instanceof br.com.ucsal.olimpiadas.domain.QuestaoMultiplaEscolha) {
                ((br.com.ucsal.olimpiadas.domain.QuestaoMultiplaEscolha) questao).setId(currentId++);
            }
        }
        store.put(questao.getId(), questao);
        return questao;
    }

    @Override
    public Questao findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Questao> findAll() {
        return new ArrayList<>(store.values());
    }
}