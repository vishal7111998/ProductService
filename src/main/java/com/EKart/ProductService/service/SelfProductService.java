package com.EKart.ProductService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.EKart.ProductService.exceptions.ProductNotFoundException;
import com.EKart.ProductService.models.Category;
import com.EKart.ProductService.models.Product;
import com.EKart.ProductService.repository.CategoryRepository;
import com.EKart.ProductService.repository.ProductRepository;

@Service
@Primary
public class SelfProductService implements ProductService{
	
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	
	public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product getProductByid(long id) throws ProductNotFoundException {
		Optional<Product> productOptional = productRepository.findById(id);
		if(productOptional.isEmpty()){
			throw new ProductNotFoundException(404L, "Product with id : " + id + " not found");
		}

		return productOptional.get();
	}

	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {
		List<Product> productList = productRepository.findAll();
		if(productList.isEmpty()){
			throw new ProductNotFoundException(404L, "Sorry :( Products are out of stock");
		}

		return productList;
	}

	@Override
	public Product replaceProduct(long id, Product product) throws ProductNotFoundException {
		Optional<Product> productOptional = productRepository.findById(id);
		if(productOptional.isEmpty()){
			throw new ProductNotFoundException(404L, "Product with id : " + id + " not found");
		}

		Product p = productOptional.get();
		p.setCategory(product.getCategory());
		p.setDescription(product.getDescription());
		p.setTitle(product.getTitle());

		product = productRepository.save(p);
		return product;
	}

	@Override
	public Product updateProduct(long id, Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createProduct(Product product) {
		/*
		Category category = product.getCategory();
		if(category.getId() == 0L) {
			category = categoryRepository.save(category);
			product.setCategory(category);
		}
		*/
		product = productRepository.save(product);
		return product;
	}

	@Override
	public Product removeProduct(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
