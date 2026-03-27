package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Questao;
import java.util.List;

public interface QuestaoRepository {
    Questao save(Questao questao);
    Questao findById(Long id);
    List<Questao> findAll();
}