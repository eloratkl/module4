package com.ntu.edu.group5.ecommerce.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntu.edu.group5.ecommerce.entity.Category;
import com.ntu.edu.group5.ecommerce.entity.Product;
import com.ntu.edu.group5.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // READ (GET ALL with filter option)
    @GetMapping({ "", "/" })
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Double minAmount,
            @RequestParam(required = false) Double maxAmount) {
        ArrayList<Product> results = new ArrayList<>(productService.getAllProducts());
        // @RequestParam(required) if set to false, client side need not provide
        // parameter, so arguments can be null
        // take note to user Wrapper classes, i.e. Double instead of primitive double
        // type, so that it can be null

        // client side provided 'category' parameter, so we filter by 'category'
        if (category != null) {
            results = productService.findProductsByCategory(category, results);
        }

        // client side provided 'minAmount' and/or 'maxAmount' parameter, so we filter
        // by amount
        if (minAmount != null || maxAmount != null) {
            results = productService.findProductsByAmount(minAmount, maxAmount, results);
        }
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    // READ (GET ALL)
    // @GetMapping({ "/", "" })
    // public ResponseEntity<ArrayList<Product>> getAllProducts() {
    //     ArrayList<Product> allProducts = productService.getAllProducts();
    //     return new ResponseEntity<>(allProducts, HttpStatus.OK);
    // }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product foundProduct = productService.getProduct(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    // CREATE
    @PostMapping({ "", "/" })
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

// TO-DO search using range of budget & category (filter)