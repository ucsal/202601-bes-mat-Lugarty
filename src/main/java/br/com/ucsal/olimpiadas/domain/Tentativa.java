package br.com.ucsal.olimpiadas.domain;

import java.util.ArrayList;
import java.util.List;

public class Tentativa {
    private Long id;
    private Long participanteId;
    private Long provaId;
    private List<Resposta> respostas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    public Long getProvaId() {
        return provaId;
    }

    public void setProvaId(Long provaId) {
        this.provaId = provaId;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}