package br.com.jonataslaet.controller.erro;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Quando há uma exceção em algum controller, é aqui que o spring entra
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String erro = "Requisição JSON malformada";
//		System.out.println(ex.getMostSpecificCause().getLocalizedMessage());
		ErroDeApi erroDeApi = new ErroDeApi(HttpStatus.BAD_REQUEST, erro, ex);
		return construtorDaEntidadeResposta(erroDeApi);
	}
	
	private ResponseEntity<Object> construtorDaEntidadeResposta(ErroDeApi ErroDeApi) {
		return new ResponseEntity<>(ErroDeApi, ErroDeApi.getStatus());
	}
}