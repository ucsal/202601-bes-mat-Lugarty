package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.domain.Prova;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import java.util.List;

public class ProvaService {
    private final ProvaRepository repository;

    public ProvaService(ProvaRepository repository) {
        this.repository = repository;
    }

    public Prova criar(String titulo) {
        Prova p = new Prova();
        p.setTitulo(titulo);
        return repository.save(p);
    }

    public List<Prova> listarTodas() {
        return repository.findAll();
    }

    public Prova buscarPorId(Long id) {
        return repository.findById(id);
    }
}