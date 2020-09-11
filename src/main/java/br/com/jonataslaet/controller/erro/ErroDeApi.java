package br.com.jonataslaet.controller.erro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroDeApi {
	
	private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime instante;
    private String messagemParaCliente;
    private String mensagemParaDesenvolvedor;
    private List<SubErroDeApi> subErros;

	public ErroDeApi() {
        instante = LocalDateTime.now();
    }
	
	public ErroDeApi(HttpStatus status) {
    	this.instante = LocalDateTime.now();
        this.status = status;
    }

    public ErroDeApi(HttpStatus status, String message, Throwable ex) {
    	this.instante = LocalDateTime.now();
        this.status = status;
        this.messagemParaCliente = message;
        this.mensagemParaDesenvolvedor = ExceptionUtils.getMessage(ex);
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
	
	public List<SubErroDeApi> getSubErros() {
		return subErros;
	}

	public void setSubErros(List<SubErroDeApi> subErros) {
		this.subErros = subErros;
	}

	public void adicionaEmErrosDeValidacaoDeCampo(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::adicionaErroDeValidacaoDeCampo);
    }
	
	private void adicionaErroDeValidacaoDeCampo(FieldError fieldError) {
        adicionaSubErro(new ErroDeValidacaoDeApi(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
    }
	
	public void adicionaEmErrosDeValidacaoDeObjeto(List<ObjectError> errosGlobais) {
        errosGlobais.forEach(this::adicionaErroDeObjeto);
    }
	
	private void adicionaErroDeObjeto(ObjectError objectError) {
        adicionaSubErro(new ErroDeValidacaoDeApi( objectError.getObjectName(),objectError.getDefaultMessage()));
    }
	
	private void adicionaSubErro(SubErroDeApi subErro) {
        if (subErros == null) {
            subErros = new ArrayList<>();
        }
        subErros.add(subErro);
    }
}
