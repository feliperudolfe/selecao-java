package com.indracompany.selecaojava.recurso.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.indracompany.comuns.modelo.dto.Mensagem;
import com.indracompany.comuns.modelo.dto.Resposta;
import com.indracompany.comuns.tratamento.NegocioException;
import com.indracompany.selecaojava.app.mensagem.Msg;
import com.indracompany.selecaojava.app.mensagem.MsgEnum;

/**
 * Handler para alterar estrutura de retorno para o objeto padrão 'Resposta'
 *
 *
 * @author 	Felipe Rudolfe Carvalho Pinheiro
 * @since   18 de abr de 2020
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestResponseExceptionHandler.class);

	@ExceptionHandler(NegocioException.class)
	protected ResponseEntity<Object> tratarNegocioException(NegocioException ex, WebRequest request) {

		Resposta resposta = new Resposta(ex.getMensagens());
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> tratarOutrasExceptions(Exception ex, WebRequest request) {

		LOG.error(ex.getMessage(), ex);

		Resposta resposta = new Resposta(null, new Mensagem(Mensagem.ERRO, Msg.get(MsgEnum.MSG_ERRO_PADRAO)));
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return tratarMethodArgumentNotValidException(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return tratarMissingServletRequestParameterException(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		return tratarBindException(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		LOG.error(ex.getMessage(), ex);

		return super.handleHttpMessageNotReadable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Resposta resposta = new Resposta(new Mensagem(Mensagem.ALERTA, ex.getMessage()));
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> tratarMissingServletRequestParameterException(
			MissingServletRequestParameterException ex, WebRequest request) {

		Resposta resposta = new Resposta(new Mensagem(Mensagem.ALERTA, ex.getMessage()));
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> tratarBindException(BindException ex,  WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		return handleExceptionInternal(ex, processFieldErrors(fieldErrors), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> tratarMethodArgumentNotValidException(MethodArgumentNotValidException ex,  WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

		return handleExceptionInternal(ex, processFieldErrors(fieldErrors), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private Resposta processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {

		List<Mensagem> mensagens = new ArrayList<>();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			mensagens.add(new Mensagem(Mensagem.ALERTA, fieldError.getDefaultMessage()));
		}

		return new Resposta(null, mensagens);
	}

}