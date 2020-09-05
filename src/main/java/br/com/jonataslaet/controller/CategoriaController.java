package br.com.jonataslaet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService cs;
	
	@GetMapping
	public List<Categoria> listar(){
		return cs.listar();
	}
}
