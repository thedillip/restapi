package com.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.api.response.ApiExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ApiExceptionResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiExceptionResponse build = ApiExceptionResponse.builder().exceptionMessage(message)
				.success(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<>(build, HttpStatus.NOT_FOUND);
	}
}
