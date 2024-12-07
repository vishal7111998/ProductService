package com.EKart.ProductService.exceptions;

@SuppressWarnings("serial")
public class ProductNotFoundException extends Exception{
	
	private long errorCode;
	
	public ProductNotFoundException(long errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(long errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
