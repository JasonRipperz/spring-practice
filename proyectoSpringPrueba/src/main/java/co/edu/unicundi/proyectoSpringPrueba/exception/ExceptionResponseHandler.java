package co.edu.unicundi.proyectoSpringPrueba.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.edu.unicundi.proyectoSpringPrueba.dto.ExceptionResponse;

//@ControllerAdvice
//@RestController
@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

	/**
	 * Body incorrecto
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE.name(),
				"Se envío un body con formato incorrecto, solo se admite JSON - " + e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Json mal constituido
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST.name(), "Body inexistente o mal construido debe ser formato JSON ",
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método HTTP no válido
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.METHOD_NOT_ALLOWED.value()),
				HttpStatus.METHOD_NOT_ALLOWED.name(), "Se esta utilizando un método HTTP no válido - " + e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/**
	 * Validación de campos
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity<ExceptionResponse> filtroFieldValidationException(FieldValidationException e,
			WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.BAD_REQUEST.value()),
				HttpStatus.BAD_REQUEST.name(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Objeto repetido
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(RepeatedObjectException.class)
	public ResponseEntity<ExceptionResponse> filtroRepeatedObjectException(RepeatedObjectException e,
			WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.CONFLICT.value()),
				HttpStatus.CONFLICT.name(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	}

	/**
	 * Objeto no encontrado
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ExceptionResponse> filtroObjectNotFoundException(ObjectNotFoundException e,
			WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.NOT_FOUND.value()),
				HttpStatus.NOT_FOUND.name(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Excepción general
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> filtroException(Exception e, WebRequest request) {
		e.printStackTrace();
		ExceptionResponse response = new ExceptionResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()),
				HttpStatus.INTERNAL_SERVER_ERROR.name(), "Error inesperado - " + e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * Alternativa para obtener la url actual
	 * ((ServletWebRequest)request).getRequest().getRequestURI().toString()
	 */

}
