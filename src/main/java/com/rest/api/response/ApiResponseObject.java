package com.rest.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.rest.api.util.ProjectConstant;

@SuppressWarnings("serial")
public class ApiResponseObject implements Serializable {
	private String timestamp;
	private String message;

	public ApiResponseObject() {
		this.timestamp = ProjectConstant.formattedDateTime(LocalDateTime.now());
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiResponseObject [timestamp=" + timestamp + ", message=" + message + "]";
	}
}
