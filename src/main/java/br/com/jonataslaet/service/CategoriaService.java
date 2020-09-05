package br.com.jonataslaet.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.controller.cadastro.CadastroCategoria;
import br.com.jonataslaet.controller.dto.CategoriaDto;
import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;

	public ResponseEntity<List<CategoriaDto>> listar() {
		List<CategoriaDto> categoriasDto = CategoriaDto.categoriasDto(cr.findAll());
		return ResponseEntity.ok(categoriasDto);
	}

	public ResponseEntity<Categoria> criarCategoria(CadastroCategoria cadastroCategoria) {

		Categoria categoria = new Categoria(cadastroCategoria.getNome());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getCodigo()).toUri();
		
		cr.save(categoria);
		
		return ResponseEntity.created(location).body(categoria);
	}
}
