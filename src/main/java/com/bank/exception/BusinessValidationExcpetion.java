package com.bank.exception;

public class BusinessValidationExcpetion extends RuntimeException{

	private String errorMessage;

	public BusinessValidationExcpetion(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
