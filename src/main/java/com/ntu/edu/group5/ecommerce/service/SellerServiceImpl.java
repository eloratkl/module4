package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntu.edu.group5.ecommerce.entity.Product;
import com.ntu.edu.group5.ecommerce.entity.Seller;
import com.ntu.edu.group5.ecommerce.exception.SellerNotFoundException;
import com.ntu.edu.group5.ecommerce.repository.ProductRepository;
import com.ntu.edu.group5.ecommerce.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {
    private SellerRepository sellerRepository;
    private ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

    public SellerServiceImpl(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ArrayList<Seller> searchSellers(String firstName) {
        List<Seller> foundSellers = sellerRepository.findByFirstName(firstName);
        logger.info("🔵 The list of sellers found by the searched firstName: " + firstName + " is: " + foundSellers);

        return (ArrayList<Seller>) foundSellers;
    }

    @Override
    public Seller createSeller(Seller seller) {
        Seller newSeller = sellerRepository.save(seller);
        logger.info("🔵 The new seller created is: " + seller);
        for (Product product : newSeller.getProducts()) {
            product.setSeller(seller);
            logger.info("🔵 The product " + product + " is tagged to seller " + seller);
            productRepository.save(product);
        }
        return newSeller;
    }

    @Override
    public Seller getSeller(Long id) {
        // Optional<Seller> optionalSeller = sellerRepository.findById(id);
        // if(optionalSeller.isPresent()) {
        // Seller foundSeller = optionalSeller.get();
        // return foundSeller;
        // }
        // throw new SellerNotFoundException(id);
        logger.info("🔵 Seller founded by the id: " + id + " is " + sellerRepository.findById(id));
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
    }

    @Override
    public ArrayList<Seller> getAllSellers() {
        List<Seller> allSellers = sellerRepository.findAll();
        logger.info("🔵 This is the list of all sellers: " + allSellers);
        return (ArrayList<Seller>) allSellers;
    }


    @Override
    public Seller updateSeller(Long id, Seller seller) {
        // retrieve the seller from the database
        // [Activity 1 - Refactor code]
        Seller sellerToUpdate = sellerRepository.findById(id)
                .orElseThrow(() -> new SellerNotFoundException(id));
        logger.info("🔵This is the seller to be updated " + sellerToUpdate);
        // update the seller retrieved from the database
        sellerToUpdate.setFirstName(seller.getFirstName());
        sellerToUpdate.setLastName(seller.getLastName());
        sellerToUpdate.setEmail(seller.getEmail());
        sellerToUpdate.setContactNo(seller.getContactNo());
        sellerToUpdate.setPassword(seller.getPassword());
        sellerToUpdate.setProducts(seller.getProducts());

        // save the updated seller back to the database
        return sellerRepository.save(sellerToUpdate);
    }

    @Override
    public void deleteSeller(Long id) {
        logger.info("🔵 Seller to be deleted " + sellerRepository.findById(id));
        sellerRepository.deleteById(id);
    }

    @Override
    public Product addProductToSeller(Long id, Product product) {
        // retrieve the seller from the database
        // [Activity 1 - Refactor code]
        Seller selectedSeller = sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
        logger.info("🔵 Added product " + product + " to seller " + sellerRepository.findById(id));
        // add the seller to the product
        product.setSeller(selectedSeller);
        // save the product to the database
        return productRepository.save(product);
    }

}
