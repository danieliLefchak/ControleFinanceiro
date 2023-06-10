package trabalhoFinal.pw25s.TrabalhoFinal.erros;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ManipuladorDeExcecoes {
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi manipulaExcecoesDeValidacao(MethodArgumentNotValidException excecao, 
													HttpServletRequest requisicao) {
		
		BindingResult resultado = excecao.getBindingResult();
		
		Map<String, String> errosValidacao = new HashMap<>();
		
		for(FieldError fieldError: resultado.getFieldErrors()) {
			errosValidacao.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return new ErrosApi(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validacao! Exceção de argumento de metodo invalido.", 
				requisicao.getServletPath(), errosValidacao);
	}
	
	@ExceptionHandler({IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi manipulaExcecoesDeValidacao(IllegalStateException excecao, 
													HttpServletRequest requisicao) {
		
		return new ErrosApi(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validacao! Exceção de estado ilegal.", 
				requisicao.getServletPath(), null);
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi manipulaExcecoesDeValidacao(HttpMessageNotReadableException excecao, 
													HttpServletRequest requisicao) {
		
		return new ErrosApi(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validacao! Exceção de mensagem http ilegivel.", 
				requisicao.getServletPath(), null);
	}
	
	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi manipulaExcecoesDeSQL(ConstraintViolationException excecao, 
													HttpServletRequest requisicao) {
		
		return new ErrosApi(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validacao! Exceção de violação de restrição.", 
				requisicao.getServletPath(), null);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrosApi manipulaExcecoesDeSQL(DataIntegrityViolationException excecao, 
													HttpServletRequest requisicao) {
		
		return new ErrosApi(HttpStatus.BAD_REQUEST.value(), 
				"Erro de validacao! Exceção de violação de "
				+ "integridade de dados.", 
				requisicao.getServletPath(), null);
	}
}
