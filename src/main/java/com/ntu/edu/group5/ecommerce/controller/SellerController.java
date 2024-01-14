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

import com.ntu.edu.group5.ecommerce.entity.Product;
import com.ntu.edu.group5.ecommerce.entity.Seller;
import com.ntu.edu.group5.ecommerce.service.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private SellerService sellerService;

    // @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<Seller>> searchSellers(@RequestParam String firstName) {
        ArrayList<Seller> foundSellers = sellerService.searchSellers(firstName);
        return new ResponseEntity<>(foundSellers, HttpStatus.OK);
    }

    // CREATE *****
    @PostMapping("")
    public ResponseEntity<Seller> createSeller(@Valid @RequestBody Seller seller) {

        // if(bindingResult.hasErrors()) {
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        Seller newSeller = sellerService.createSeller(seller);
        return new ResponseEntity<>(newSeller, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<Seller>> getAllSellers() {
        ArrayList<Seller> allSellers = sellerService.getAllSellers();
        return new ResponseEntity<>(allSellers, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long id) {
        Seller foundSeller = sellerService.getSeller(id);
        return new ResponseEntity<>(foundSeller, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Nested route - add product to seller
    @PostMapping("/{id}/products")
    public ResponseEntity<Product> addProductToSeller(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product newProduct = sellerService.addProductToSeller(id, product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

}
