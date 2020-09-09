package br.com.jonataslaet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.controller.dto.LancamentoDto;
import br.com.jonataslaet.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	LancamentoService ls;
	
	@GetMapping
	public ResponseEntity<List<LancamentoDto>> listar(){
		return ls.listar();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoDto> buscarLancamento(@PathVariable Long id){
		return ls.buscarLancamento(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletarLancamento(@PathVariable Long id){
		return ls.deletarLancamento(id);
	}
}
