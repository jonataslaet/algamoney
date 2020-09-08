package br.com.jonataslaet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.controller.cadastro.CadastroPessoa;
import br.com.jonataslaet.controller.dto.PessoaDto;
import br.com.jonataslaet.model.Pessoa;
import br.com.jonataslaet.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaService ps;
	
	@GetMapping
	public ResponseEntity<List<PessoaDto>> listar(){
		return ps.listar();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody CadastroPessoa categoria){
		return ps.criarPessoa(categoria);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDto> buscarPessoa(@PathVariable Long id){
		return ps.buscarPessoa(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarPessoa(@PathVariable Long id){
		return ps.deletarPessoa(id);
	}
}
