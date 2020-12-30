package com.bank.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BusinessValidationExcpetion.class)
	public ResponseEntity<Object> handleExcpetion(BusinessValidationExcpetion businessValidationExcpetion) {
		List<String> details = new ArrayList<String>();
		details.add(businessValidationExcpetion.getErrorMessage());
		ErrorResponse errorResponse = new ErrorResponse(businessValidationExcpetion.getMessage(), details);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
