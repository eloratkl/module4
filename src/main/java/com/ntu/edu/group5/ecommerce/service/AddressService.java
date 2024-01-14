package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.Address;

public interface AddressService {

    Address createAddress(Address address);

    Address getAddress(Long id);

    ArrayList<Address> getAllAddresses();

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

    ArrayList<Address> searchAddressesByCity(String city);
}

