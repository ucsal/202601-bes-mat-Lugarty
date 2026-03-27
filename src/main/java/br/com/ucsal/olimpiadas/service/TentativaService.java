package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.*;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import java.util.List;

public class TentativaService {
    private final TentativaRepository tentativaRepository;
    private final QuestaoRepository questaoRepository;

    public TentativaService(TentativaRepository tentativaRepository, QuestaoRepository questaoRepository) {
        this.tentativaRepository = tentativaRepository;
        this.questaoRepository = questaoRepository;
    }

    public Tentativa iniciarTentativa(Long participanteId, Long provaId, List<Resposta> respostas) {
        Tentativa t = new Tentativa();
        t.setParticipanteId(participanteId);
        t.setProvaId(provaId);
        t.setRespostas(respostas);
        return tentativaRepository.save(t);
    }

    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (Resposta r : tentativa.getRespostas()) {
            Questao q = questaoRepository.findById(r.getQuestaoId());
            if (q != null && q.isRespostaCorreta(r.getAlternativaMarcada())) {
                acertos++;
            }
        }
        return acertos;
    }

    public List<Tentativa> listarTodas() {
        return tentativaRepository.findAll();
    }
}