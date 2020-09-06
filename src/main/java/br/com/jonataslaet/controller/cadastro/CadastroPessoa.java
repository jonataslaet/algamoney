package br.com.jonataslaet.controller.cadastro;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jonataslaet.model.Endereco;

public class CadastroPessoa {
	
	@NotNull
	@Size(min=5, max=50)
	private String nome;
	private boolean ativo;
	private Endereco endereco;

	public CadastroPessoa() {
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
