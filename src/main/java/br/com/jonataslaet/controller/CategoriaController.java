package br.com.jonataslaet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.controller.cadastro.CadastroCategoria;
import br.com.jonataslaet.controller.dto.CategoriaDto;
import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService cs;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDto>> listar(){
		return cs.listar();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criarCategoria(@RequestBody CadastroCategoria categoria){
		return cs.criarCategoria(categoria);
	}
}
