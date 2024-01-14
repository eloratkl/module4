package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.Category;
import com.ntu.edu.group5.ecommerce.entity.Product;

public interface ProductService {

    // Product createProduct(Product product);

    // Product getProduct(Long id);

    // ArrayList<Product> getAllProducts();

    // Product updateProduct(Long id, Product product);

    // void deleteProduct(Long id);

    // ArrayList<Product> searchProducts(String name);

    Product createProduct(Product product);

    Product getProduct(Long id);

    ArrayList<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    ArrayList<Product> findProductsByCategory(Category category, ArrayList<Product> unfilteredProducts);

    ArrayList<Product> findProductsByAmount(Double minAmount, Double maxAmount, ArrayList<Product> unfilteredProducts);

    ArrayList<Product> searchProducts(String name);


}