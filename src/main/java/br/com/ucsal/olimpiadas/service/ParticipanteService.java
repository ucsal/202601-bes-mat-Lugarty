package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Participante;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import java.util.List;

public class ParticipanteService {
    private final ParticipanteRepository repository;

    public ParticipanteService(ParticipanteRepository repository) {
        this.repository = repository;
    }

    public Participante cadastrar(String nome, String email) {
        Participante p = new Participante();
        p.setNome(nome);
        p.setEmail(email);
        return repository.save(p);
    }

    public List<Participante> listarTodos() {
        return repository.findAll();
    }

    public Participante buscarPorId(Long id) {
        return repository.findById(id);
    }
}