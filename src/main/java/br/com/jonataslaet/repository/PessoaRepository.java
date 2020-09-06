package br.com.jonataslaet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonataslaet.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
