package br.com.jonataslaet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
