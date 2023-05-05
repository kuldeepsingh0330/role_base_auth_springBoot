package com.ransankul.roleBaseAuth.exception;


@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	//THIs is a custom Exception is throw when the requested resource not found 
	
	private Long id;
	private String message;
	
	
	
	public ResourceNotFoundException(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
