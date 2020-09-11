package br.com.jonataslaet.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.controller.cadastro.CadastroCategoria;
import br.com.jonataslaet.controller.dto.CategoriaDto;
import br.com.jonataslaet.controller.erro.ObjectNotFoundException;
import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.repository.CategoriaRepository;
import br.com.jonataslaet.utilidade.Utils;

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
		cr.save(categoria);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(categoria.getCodigo()).toUri();
		
		return ResponseEntity.created(location).body(categoria);
	}

	public ResponseEntity<CategoriaDto> buscarCategoria(Long codigo) {
		Optional<Categoria> categoria = cr.findById(codigo);
		if (!categoria.isPresent()) {
			throw new ObjectNotFoundException("Categoria não encontrada");
		}
		CategoriaDto categoriaDto = new CategoriaDto(categoria.get());
		return ResponseEntity.ok().body(categoriaDto);
	}
	
	public ResponseEntity<Categoria> deletarCategoria(Long codigo) {
		Optional<Categoria> categoria = cr.findById(codigo);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		cr.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<?> atualizarCategoria(Long codigo, CadastroCategoria cadastroNovoCategoria) throws IllegalArgumentException, IllegalAccessException {
		Optional<Categoria> categoria = cr.findById(codigo);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Categoria categoriaAtualizada = categoria.get();
		Categoria categoriaQueAtualiza = new Categoria(cadastroNovoCategoria.getNome());
		
		Utils.atualizarObjeto(categoriaAtualizada, categoriaQueAtualiza);
		
		cr.save(categoriaAtualizada);
		return ResponseEntity.noContent().build();
	}
}
