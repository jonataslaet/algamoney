package br.com.jonataslaet.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.controller.cadastro.CadastroPessoa;
import br.com.jonataslaet.controller.dto.PessoaDto;
import br.com.jonataslaet.model.Pessoa;
import br.com.jonataslaet.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pr;

	public ResponseEntity<List<PessoaDto>> listar() {
		List<PessoaDto> pessoasDto = PessoaDto.pessoasDto(pr.findAll());
		return ResponseEntity.ok(pessoasDto);
	}

	public ResponseEntity<Pessoa> criarPessoa(CadastroPessoa cadastroPessoa) {

		Pessoa pessoa = new Pessoa(cadastroPessoa.getNome(), cadastroPessoa.isAtivo(), cadastroPessoa.getEndereco());
		pr.save(pessoa);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(pessoa.getCodigo()).toUri();
		
		return ResponseEntity.created(location).body(pessoa);
	}

	public ResponseEntity<PessoaDto> buscarPessoa(Long codigo) {
		Optional<Pessoa> pessoa = pr.findById(codigo);
		if (!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		PessoaDto pessoaDto = new PessoaDto(pessoa.get());
		return ResponseEntity.ok().body(pessoaDto);
	}

	public ResponseEntity<?> deletarPessoa(Long codigo) {
		Optional<Pessoa> pessoa = pr.findById(codigo);
		if (!pessoa.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		pr.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
}
