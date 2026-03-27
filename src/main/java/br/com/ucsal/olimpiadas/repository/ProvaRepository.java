package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Prova;
import java.util.List;

public interface ProvaRepository {
    Prova save(Prova prova);
    Prova findById(Long id);
    List<Prova> findAll();
}