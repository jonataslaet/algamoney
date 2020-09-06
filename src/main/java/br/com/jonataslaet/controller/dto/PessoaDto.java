package br.com.jonataslaet.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jonataslaet.model.Pessoa;

public class PessoaDto {

	private Long codigo;
	private String nome;
	private boolean ativo;
	
	public PessoaDto(Pessoa pessoa) {
		this.codigo = pessoa.getCodigo();
		this.nome = pessoa.getNome();
		this.ativo = pessoa.isAtivo();
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
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public static List<PessoaDto> pessoasDto(List<Pessoa> pessoas){
		return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
	
}
