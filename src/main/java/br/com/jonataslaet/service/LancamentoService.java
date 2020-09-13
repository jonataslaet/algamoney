package br.com.jonataslaet.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.controller.cadastro.CadastroLancamento;
import br.com.jonataslaet.controller.dto.LancamentoDto;
import br.com.jonataslaet.controller.erro.ObjectNotFoundException;
import br.com.jonataslaet.controller.erro.PessoaInativaException;
import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.model.Pessoa;
import br.com.jonataslaet.repository.CategoriaRepository;
import br.com.jonataslaet.repository.LancamentoRepository;
import br.com.jonataslaet.repository.PessoaRepository;
import br.com.jonataslaet.repository.filter.LancamentoFilter;

@Service
public class LancamentoService {

	@Autowired
	LancamentoRepository lr;
	
	@Autowired
	CategoriaRepository cr;
	
	@Autowired
	PessoaRepository pr;

	public ResponseEntity<List<LancamentoDto>> listar(LancamentoFilter lancamentoFilter) {
		List<LancamentoDto> lancamentosDto = LancamentoDto.lancamentosDto(lr.filtrar(lancamentoFilter));
		return ResponseEntity.ok(lancamentosDto);
	}

	public ResponseEntity<LancamentoDto> buscarLancamento(Long codigo) {
		Optional<Lancamento> lancamento = lr.findById(codigo);
		if (!lancamento.isPresent()) {
			throw new ObjectNotFoundException("Lançamento não encontrado");
		}
		LancamentoDto lancamentoDto = new LancamentoDto(lancamento.get());
		return ResponseEntity.ok().body(lancamentoDto);
	}

	public ResponseEntity<?> deletarLancamento(Long codigo) {
		Optional<Lancamento> lancamento = lr.findById(codigo);
		if (!lancamento.isPresent()) {
			throw new ObjectNotFoundException("Lançamento não encontrado");
		}
		lr.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Lancamento> cadastrarLancamento(@Valid CadastroLancamento cadastroLancamento) {
		Lancamento lancamento = new Lancamento(cadastroLancamento.getDescricao(),
				cadastroLancamento.getDataVencimento(), cadastroLancamento.getDataPagamento(),
				cadastroLancamento.getValor(), cadastroLancamento.getObservacao(), cadastroLancamento.getTipo());
		
		Optional<Categoria> categoriaNoBanco = cr.findById(cadastroLancamento.getCategoria().getCodigo());
		Optional<Pessoa> pessoaNoBanco = pr.findById(cadastroLancamento.getPessoa().getCodigo());
		if (!categoriaNoBanco.isPresent()) {
			throw new ObjectNotFoundException("Categoria não encontrada");
		}
		
		if (!pessoaNoBanco.isPresent()) {
			throw new ObjectNotFoundException("Pessoa não encontrada");
		}
		
		Categoria categoria = categoriaNoBanco.get();
		lancamento.setCategoria(categoria);
		
		Pessoa pessoa = pessoaNoBanco.get();		

		if (!pessoa.isAtivo()) {
			throw new PessoaInativaException();
		}
		
		lancamento.setPessoa(pessoa);
		lr.save(lancamento);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(lancamento.getCodigo()).toUri();
		
		return ResponseEntity.created(location).body(lancamento);
	}
}
