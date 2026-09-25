package com.qa.api.errors;

public class APIException extends RuntimeException {
	
	public APIException(String errormessage) {
		super(errormessage);
	}

}
