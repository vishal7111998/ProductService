package com.EKart.ProductService.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.EKart.ProductService.dto.ProductNotFoundExceptionDto;
import com.EKart.ProductService.exceptions.ProductNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
		ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
		productNotFoundExceptionDto.setErrorCode(e.getErrorCode());
		productNotFoundExceptionDto.setMessage(e.getMessage());
		
		return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
	}
}
