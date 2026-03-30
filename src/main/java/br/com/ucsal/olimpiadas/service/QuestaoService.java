package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Questao;
import br.com.ucsal.olimpiadas.domain.QuestaoMultiplaEscolha;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import java.util.List;

public class QuestaoService {
    private final QuestaoRepository repository;

    public QuestaoService(QuestaoRepository repository) {
        this.repository = repository;
    }

    // Mantém o método original que o App espera
    public Questao adicionarQuestao(Long provaId, String enunciado, String[] alternativas, char alternativaCorreta) {
        QuestaoMultiplaEscolha q = new QuestaoMultiplaEscolha();
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(alternativaCorreta);
        return repository.save(q);
    }

    public List<Questao> listarQuestoesPorProva(Long provaId) {
        return repository.findAll().stream()
                .filter(q -> q.getProvaId().equals(provaId))
                .toList();
    }

    public Questao buscarPorId(Long id) {
        return repository.findById(id);
    }
}