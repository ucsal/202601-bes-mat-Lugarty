package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.domain.Tentativa;
import java.util.List;

public interface TentativaRepository {
    Tentativa save(Tentativa tentativa);
    Tentativa findById(Long id);
    List<Tentativa> findAll();
}