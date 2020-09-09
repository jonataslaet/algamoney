package br.com.jonataslaet.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jonataslaet.model.Categoria;
import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.model.Pessoa;
import br.com.jonataslaet.model.TipoLancamento;

public class LancamentoDto {

	private Long codigo;
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento;
	
	private BigDecimal valor;
	private String observacao;
	private TipoLancamento tipo;
	private Categoria categoria;
	private Pessoa pessoa;

	public LancamentoDto(Lancamento lancamento) {
		this.codigo = lancamento.getCodigo();
		this.descricao = lancamento.getDescricao();
		this.dataVencimento = lancamento.getDataVencimento();
		this.dataPagamento = lancamento.getDataPagamento();
		this.valor = lancamento.getValor();
		this.observacao = lancamento.getObservacao();
		this.tipo = lancamento.getTipo();
		this.categoria = lancamento.getCategoria();
		this.pessoa = lancamento.getPessoa();
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

	public static List<LancamentoDto> lancamentosDto(List<Lancamento> lancamentos) {
		return lancamentos.stream().map(LancamentoDto::new).collect(Collectors.toList());
	}

}
