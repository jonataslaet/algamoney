package br.com.jonataslaet.controller.cadastro;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroCategoria {
	
	@NotNull
	@Size(min=5, max=50)
	private String nome;

	public CadastroCategoria() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
