package br.com.jonataslaet.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jonataslaet.controller.dto.LancamentoDto;
import br.com.jonataslaet.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	public Page<LancamentoDto> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
