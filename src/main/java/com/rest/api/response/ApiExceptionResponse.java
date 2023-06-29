package com.rest.api.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiExceptionResponse extends ApiResponseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionMessage;
	private boolean success;
	private HttpStatus status;
}
