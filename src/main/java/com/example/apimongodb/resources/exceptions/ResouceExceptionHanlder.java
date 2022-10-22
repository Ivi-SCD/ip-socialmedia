package com.example.apimongodb.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.apimongodb.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResouceExceptionHanlder {
	
	@ExceptionHandler
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,
			HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(System.currentTimeMillis(), 
				status.value(), "Not founded", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(error);
	}
}