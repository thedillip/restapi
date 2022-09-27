package com.rest.api.response;

import java.time.LocalDateTime;

public class ApiResponse {
	private LocalDateTime timestamp = LocalDateTime.now();
	private String message;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ApiResponse() {
		super();
	}
	public ApiResponse(LocalDateTime timestamp, String message) {
		super();
		this.timestamp = LocalDateTime.now();
		this.message = message;
	}
	
	
}
