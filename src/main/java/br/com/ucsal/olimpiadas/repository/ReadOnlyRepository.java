package br.com.ucsal.olimpiadas.repository;

import java.util.List;

public interface ReadOnlyRepository<T, ID> {
    T findById(ID id);
    List<T> findAll();
}