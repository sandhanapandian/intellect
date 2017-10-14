package com.intellect.response;

public class ValidationError {

	private String code;
	private String field;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	public ValidationError() {
		// TODO Auto-generated constructor stub
	}

	public ValidationError(String code, String msg, String field) {
		

		this.code = code;
		this.field = field;
		this.message = msg;
		// TODO Auto-generated constructor stub
	}
	
}
