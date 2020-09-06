package br.com.jonataslaet.controller.erro;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroDeApi {
	
	private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime instante;
    private String messagemParaCliente;
    private String mensagemParaDesenvolvedor;

	public ErroDeApi() {
        instante = LocalDateTime.now();
    }

    public ErroDeApi(HttpStatus status, String message, Throwable ex) {
    	this.instante = LocalDateTime.now();
        this.status = status;
        this.messagemParaCliente = message;
        this.mensagemParaDesenvolvedor = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public void setInstante(LocalDateTime instante) {
		this.instante = instante;
	}

	public String getMessagemParaCliente() {
		return messagemParaCliente;
	}

	public void setMessagemParaCliente(String messagemParaCliente) {
		this.messagemParaCliente = messagemParaCliente;
	}

	public String getMensagemParaDesenvolvedor() {
		return mensagemParaDesenvolvedor;
	}

	public void setMensagemParaDesenvolvedor(String mensagemParaDesenvolvedor) {
		this.mensagemParaDesenvolvedor = mensagemParaDesenvolvedor;
	}

}
