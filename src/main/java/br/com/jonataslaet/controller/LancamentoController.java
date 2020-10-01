package br.com.jonataslaet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.controller.cadastro.CadastroLancamento;
import br.com.jonataslaet.controller.dto.LancamentoDto;
import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.repository.filter.LancamentoFilter;
import br.com.jonataslaet.repository.projection.ResumoLancamento;
import br.com.jonataslaet.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	LancamentoService ls;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<LancamentoDto> listar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return ls.listar(lancamentoFilter, pageable);
	}
	
	@GetMapping(params="resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable){
		return ls.resumir(lancamentoFilter, pageable);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<LancamentoDto> buscarLancamento(@PathVariable Long id){
		return ls.buscarLancamento(id);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<?> deletarLancamento(@PathVariable Long id){
		return ls.deletarLancamento(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento>cadastrarLancamento(@Valid @RequestBody CadastroLancamento cadastroLancamento){
		return ls.cadastrarLancamento(cadastroLancamento);
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<?> atualizarLancamento(@PathVariable Long id, @Valid @RequestBody CadastroLancamento cadastroLancamento) throws IllegalArgumentException, IllegalAccessException{
		return ls.atualizarLancamento(id, cadastroLancamento);
	}
}
