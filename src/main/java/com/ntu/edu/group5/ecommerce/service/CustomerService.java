package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import com.ntu.edu.group5.ecommerce.entity.Customer;

public interface CustomerService {

    Customer createCustomer(String firstName, String lastName,String email, String contactNo,int YOB);

    Customer getCustomer(Long id);

    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    ArrayList<Customer> searchCustomers(String firstName);
}
