package br.com.jonataslaet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;
	
	public List<Categoria> listar(){
		return cr.findAll();
	}
}
