package com.EKart.ProductService.controller;

import com.EKart.ProductService.exceptions.ProductNotFoundException;
import com.EKart.ProductService.models.Product;
import com.EKart.ProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productService;


    @Test
    void test_whenGetProductIsCalled_ReturnProducts() throws ProductNotFoundException {
        Product product = new Product();
        product.setTitle("Test");
        product.setDescription("Test");
        product.setPrice(100.20);

        when(productService.getProductByid(1l)).thenReturn(product);
        Product product2 = productController.getProductByid(1l).getBody();

        assertEquals("Test", product2.getTitle());
    }

    @Test
    void test_whenGetProductByIdIsCalled_returnProductNotFoundException() throws ProductNotFoundException {
        Product product = new Product();
        product.setTitle("Test");
        product.setDescription("Test");
        product.setPrice(100.20);

        when(productService.getProductByid(2l)).thenThrow(new ProductNotFoundException(404, "Proudct with id : " + 2l + " not found"));
        //Product product2 = productController.getProductByid(1l).getBody();

        assertThrows(ProductNotFoundException.class,
                () -> productController.getProductByid(2l));
    }

    @Test
    void test_whenGetAllProductsCalled_returnAllProducts() throws ProductNotFoundException {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setTitle("Test1");
        product1.setDescription("Test1");
        product1.setPrice(100.20);

        Product product2 = new Product();
        product2.setTitle("Test2");
        product2.setDescription("Test2");
        product2.setPrice(200.20);

        productList = List.of(product1, product2);
        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> productList2 = productController.getAllProducts();

        assertEquals(2, productList2.size());
    }

    @Test
    void test_whenReplaceProductIsCalled_returnReplacedProduct() throws ProductNotFoundException {
        Product p = new Product();
        p.setTitle("Test Replace");
        p.setDescription("Test");
        p.setPrice(100.0);

        when(productService.replaceProduct(1l, p)).thenReturn(p);
        Product p2 = productController.replaceProduct(1l, p);

        assertEquals("Test Replace", p2.getTitle());
    }

    @Test
    void test_whenUpdateProductIsCalled_returnUpdatedProduct() {
        Product p = new Product();
        p.setTitle("Test Update");
        p.setDescription("Test");
        p.setPrice(100.0);

        when(productService.updateProduct(1l, p)).thenReturn(p);
        Product p2 = productController.updateProduct(1l, p);

        assertEquals("Test Update", p2.getTitle());
    }

    @Test
    void test_whenCreateProductIsCalled_returnCreatedProduct() {
        Product p = new Product();
        p.setId(1l);
        p.setTitle("Test Create");
        p.setDescription("Test");
        p.setPrice(100.0);

        when(productService.createProduct(p)).thenReturn(p);
        Product p2 = productController.createProduct(p);

        assertEquals("Test Create", p2.getTitle());
    }

    /*
    @Test
    void removeProduct() throws ProductNotFoundException {
        long id = 1L;

        // Stubbing the behavior of the mocked service
        doNothing().when(productService).removeProduct(id);

        // Call the controller method
        productController.removeProduct(id);

        // Verify that the service method was called with the correct argument
        verify(productService, times(1)).removeProduct(id);
    }

     */

    @Test
    void test_whenRemoveProductIsCalled_returnRemovedProduct() throws ProductNotFoundException {
        long id = 1l;
        Product p = new Product();
        p.setId(1l);
        p.setTitle("Test Remove");
        p.setDescription("Test");
        p.setPrice(100.0);

        when(productService.removeProduct(1l)).thenReturn(p);
        p = productController.removeProduct(1l);

        assertEquals("Test Remove", p.getTitle());
    }
}