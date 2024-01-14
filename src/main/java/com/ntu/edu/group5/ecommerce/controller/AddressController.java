package com.ntu.edu.group5.ecommerce.controller;

import java.util.ArrayList;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ntu.edu.group5.ecommerce.entity.Address;
import com.ntu.edu.group5.ecommerce.service.AddressServiceImpl;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);


    private AddressServiceImpl addressServiceImpl;

    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl= addressServiceImpl;
    }


    // CREATE
    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) {
        Address newAddress = addressServiceImpl.createAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Address> createAddressById(@Valid @RequestBody TemplateAdress data) {
        Address newAddress = addressServiceImpl.createAddressByCustomerId(data.getCustomerId(),
                                                                    data.getBlockNumber(),
                                                                    data.getStreetName(),
                                                                    data.getBuildingName(),
                                                                    data.getCity(),
                                                                    data.getState(),
                                                                    data.getPostalCode());
        logger.info("🛒🔵 - Controller - creating newAddress ...");
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping({ "/", "" })
    public ResponseEntity<ArrayList<Address>> getAllAddresses() {
        logger.info("🛒🔵 - Controller - finding foundAllCartItems ...");
        ArrayList<Address> allAddresses = addressServiceImpl.getAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        Address foundAddress = addressServiceImpl.getAddress(id);
        return new ResponseEntity<>(foundAddress, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<Address>> searchAddresses(@RequestParam String city) {
        ArrayList<Address> foundAddresses = addressServiceImpl.searchAddressesByCity(city);
        return new ResponseEntity<>(foundAddresses, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        Address updatedAddress = addressServiceImpl.updateAddress(id, address);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
        logger.info("🛒🔵 - Controller - deleting foundAllCartItems ...");
        addressServiceImpl.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

class TemplateAdress{
    private long customerId;
    private String blockNumber;
    private String streetName;
    private String buildingName;
    private String city;
    private String state;
    private String postalCode;

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public String getBlockNumber() {
        return blockNumber;
    }
    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
