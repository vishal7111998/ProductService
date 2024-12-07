package com.EKart.ProductService.dto;

public class ProductNotFoundExceptionDto {
	private long errorCode;
	private String message;
	
	public long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
