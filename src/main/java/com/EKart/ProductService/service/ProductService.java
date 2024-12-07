package com.EKart.ProductService.service;

import java.util.List;

import com.EKart.ProductService.exceptions.ProductNotFoundException;
import com.EKart.ProductService.models.Product;

public interface ProductService {
	
	public Product getProductByid(long id) throws ProductNotFoundException;
	
	public List<Product> getAllProducts() throws ProductNotFoundException;
	
	public Product replaceProduct(long id, Product product) throws ProductNotFoundException;
	
	public Product updateProduct(long id, Product product);
	
	public Product createProduct(Product product);
	
	public Product removeProduct(long id);
}
