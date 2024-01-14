package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.Product;
import com.ntu.edu.group5.ecommerce.entity.Seller;

public interface SellerService {
   Seller createSeller(Seller seller);

    Seller getSeller(Long id);

    ArrayList<Seller> getAllSellers();

    Seller updateSeller(Long id, Seller seller);

    void deleteSeller(Long id);
    
    Product addProductToSeller(Long id, Product product);

    ArrayList<Seller> searchSellers(String firstName);

}
