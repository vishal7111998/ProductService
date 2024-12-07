package com.EKart.ProductService.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.EKart.ProductService.dto.FakeStoreProductDto;
import com.EKart.ProductService.exceptions.ProductNotFoundException;
import com.EKart.ProductService.models.Category;
import com.EKart.ProductService.models.Product;

@Service
public class FakeStoreProductService implements ProductService{
	
	private RestTemplate restTemplate;
	
	public FakeStoreProductService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Product getProductByid(long id) throws ProductNotFoundException {
		FakeStoreProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
		if(productDto == null) {
			throw new ProductNotFoundException(404L, "Product not found with id : " + id);
		}
		
		Product product = convertProductDtoToProduct(productDto);
		return product;
	}
	
	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {
		FakeStoreProductDto[] fakeStoreProductDtoList = 
				restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
		
		if(fakeStoreProductDtoList == null || fakeStoreProductDtoList.length == 0) {
			throw new ProductNotFoundException(404L, "No Product Found");
		}
		
		List<Product> productList = new ArrayList<>();
		for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList) {
			productList.add(convertProductDtoToProduct(fakeStoreProductDto));
		}
		
		return productList;
	}

	
	@Override
	public Product replaceProduct(long id, Product product) {
		FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
		fakeStoreProductDto.setId(id);
		fakeStoreProductDto.setTitle(product.getTitle());
		fakeStoreProductDto.setCategory(product.getCategory().getTitle());
		fakeStoreProductDto.setDescription(product.getDescription());
		
		//restTemplate.put(null, fakeStoreProductDto, null);
		RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = 
				restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
		
		FakeStoreProductDto fakeStoreProductDto2 = 
				restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor).getBody();
		
		Product product2 = convertProductDtoToProduct(fakeStoreProductDto2);
		return product2;
	}
	
	
	@Override
	public Product updateProduct(long id, Product product) {
		FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
		//fakeStoreProductDto.setId(id);
		fakeStoreProductDto.setTitle(product.getTitle());
		//fakeStoreProductDto.setCategory(product.getCategory().getTitle());
		fakeStoreProductDto.setDescription(product.getDescription());
		fakeStoreProductDto.setPrice(product.getPrice());
		
		FakeStoreProductDto fakeStoreProductDto2 = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeStoreProductDto, FakeStoreProductDto.class);
		Product product2 = convertProductDtoToProduct(fakeStoreProductDto2);
		return product2;
	}
	
	
	@Override
	public Product createProduct(Product product) {
		FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
		fakeStoreProductDto.setTitle(product.getTitle());
		fakeStoreProductDto.setDescription(product.getDescription());
		fakeStoreProductDto.setCategory(product.getCategory().getTitle());
		fakeStoreProductDto.setPrice(product.getPrice());
		
		FakeStoreProductDto fakeStoreProductDto2 = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
		Product product2 = convertProductDtoToProduct(fakeStoreProductDto2);
		return product2;
	}
	
	
	@Override
	public Product removeProduct(long id) {
		
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = 
				restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
		
		FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.DELETE, null, responseExtractor).getBody();
		Product product = convertProductDtoToProduct(fakeStoreProductDto);
		return product;
	}

	
	
	private Product convertProductDtoToProduct(FakeStoreProductDto productDto) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setTitle(productDto.getTitle());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		Category category = new Category();
		category.setTitle(productDto.getCategory());
		product.setCategory(category);
		
		return product;
		
	}

	
}
