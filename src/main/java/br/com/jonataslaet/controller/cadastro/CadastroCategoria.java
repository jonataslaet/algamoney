package br.com.jonataslaet.controller.cadastro;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CadastroCategoria {
	
	private Long codigo;

	@NotNull
	@Size(min = 5, max = 50)
	private String nome;

	public CadastroCategoria() {

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
