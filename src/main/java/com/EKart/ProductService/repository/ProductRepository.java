package com.EKart.ProductService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EKart.ProductService.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}