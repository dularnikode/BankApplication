package com.bank.exception;

import java.util.List;

public class ErrorResponse {
	private List<String> errorMessages;

	private String message;

	public ErrorResponse(String message, List<String> details) {
		super();
		this.errorMessages = details;
		this.message = message;
	}

	public List<String> getDetails() {
		return errorMessages;
	}

	public void setDetails(List<String> details) {
		this.errorMessages = details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
