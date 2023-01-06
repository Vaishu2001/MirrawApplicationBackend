package com.mirraw.exception;

import org.springframework.http.ResponseEntity;

public class ApiExceptionHandler {
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		return null;
		//Create a payload containing exception details
		//Return response entity
	}

}
