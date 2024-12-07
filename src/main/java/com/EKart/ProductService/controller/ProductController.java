package com.EKart.ProductService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EKart.ProductService.exceptions.ProductNotFoundException;
import com.EKart.ProductService.models.Product;
import com.EKart.ProductService.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductByid(@PathVariable("id") long id) throws ProductNotFoundException {
		Product product = null;
		product = productService.getProductByid(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping()
	public List<Product> getAllProducts() throws ProductNotFoundException{
		return productService.getAllProducts();
	}
	
	@PutMapping("/{id}")
	public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException{
		return productService.replaceProduct(id, product);
	}
	
	
	//PATCH NOT WORKING CURRENTLY
	@PatchMapping("/{id}")
	public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
	@PostMapping()
	public Product createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	
	@DeleteMapping("/{id}")
	public Product removeProduct(@PathVariable("id") long id) {
		return productService.removeProduct(id);
	}
	
	
	
	
}
