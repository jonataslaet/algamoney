package br.com.jonataslaet.repository.lancamento;

import java.util.List;

import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
