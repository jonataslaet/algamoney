package br.com.jonataslaet.controller.cadastro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.model.Pessoa;
import br.com.jonataslaet.model.TipoLancamento;

public class CadastroLancamento {

	private Long codigo;

	@NotNull
	private String descricao;

	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;

	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private TipoLancamento tipo;
	
	private String observacao;
	
	@NotNull
	private Categoria categoria;
	
	@NotNull
	private Pessoa pessoa;

	public CadastroLancamento() {

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
