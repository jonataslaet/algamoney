package br.com.jonataslaet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonataslaet.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
