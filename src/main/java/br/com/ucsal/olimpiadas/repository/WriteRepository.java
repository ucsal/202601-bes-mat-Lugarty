package br.com.ucsal.olimpiadas.repository;

public interface WriteRepository<T, ID> {
    T save(T entity);
}