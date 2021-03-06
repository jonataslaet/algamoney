package br.com.jonataslaet.controller.erro;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Quando há uma exceção em algum controller, é aqui que o spring entra
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	protected ResponseEntity<Object> handleHttpInvalidDataAccessApiUsage(InvalidDataAccessApiUsageException ex){
		String erro = "Uso incorreto da api";
		ErroDeApi apiError = new ErroDeApi(HttpStatus.INTERNAL_SERVER_ERROR, erro, ex.getMostSpecificCause());
		return construtorDaEntidadeResposta(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mensagemAmigavelDeErro = "Requisição JSON malformada";
		ErroDeApi erroDeApi = new ErroDeApi(HttpStatus.BAD_REQUEST, mensagemAmigavelDeErro, ex);
		return construtorDaEntidadeResposta(erroDeApi);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String erro = "Violação na Integridade dos Dados";
		ErroDeApi apiError = new ErroDeApi(HttpStatus.INTERNAL_SERVER_ERROR, erro, ex.getMostSpecificCause());
		return construtorDaEntidadeResposta(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErroDeApi apiError = new ErroDeApi(HttpStatus.BAD_REQUEST);
		apiError.setMessagemParaCliente("Erro de validação");
		apiError.setMensagemParaDesenvolvedor(ExceptionUtils.getMessage(ex));
		apiError.adicionaEmErrosDeValidacaoDeCampo(ex.getBindingResult().getFieldErrors());
		apiError.adicionaEmErrosDeValidacaoDeObjeto(ex.getBindingResult().getGlobalErrors());
		return construtorDaEntidadeResposta(apiError);
	}
	
	@ExceptionHandler({ObjectNotFoundException.class})
	protected ResponseEntity<Object> handleEntityNotFound(ObjectNotFoundException ex) {
		String erro = "Recurso não encontrado";
		ErroDeApi apiError = new ErroDeApi(HttpStatus.NOT_FOUND, erro, ex);
		return construtorDaEntidadeResposta(apiError);
	}
	
	@ExceptionHandler(PessoaInativaException.class)
	protected ResponseEntity<Object> handlePessoaInativa(PessoaInativaException ex){
		String mensagemDeErroAmigavel = "Pessoa inativa";
		ErroDeApi apiError = new ErroDeApi(HttpStatus.BAD_REQUEST, mensagemDeErroAmigavel, ex);
		return construtorDaEntidadeResposta(apiError);
	}
	
	private ResponseEntity<Object> construtorDaEntidadeResposta(ErroDeApi ErroDeApi) {
		return new ResponseEntity<>(ErroDeApi, ErroDeApi.getStatus());
	}
	
}