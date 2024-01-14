package com.ntu.edu.group5.ecommerce.controller;

import java.util.ArrayList;

import jakarta.validation.Valid;

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

import com.ntu.edu.group5.ecommerce.entity.Customer;
import com.ntu.edu.group5.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  // @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/search")
  public ResponseEntity<ArrayList<Customer>> searchCustomers(@RequestParam String firstName) {
    ArrayList<Customer> foundCustomers = customerService.searchCustomers(firstName);
    return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
  }

  // CREATE
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody TemplateCustomer data) {

    // if(bindingResult.hasErrors()) {
    // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }

    Customer newCustomer = customerService.createCustomer(data.getFirstName(),
                                                          data.getLastName(),
                                                          data.getEmail(),
                                                          data.getContactNo(),
                                                          data.getYearOfBirth());
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  // READ (GET ALL)
  @GetMapping("")
  public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
    ArrayList<Customer> allCustomers = customerService.getAllCustomers();
    return new ResponseEntity<>(allCustomers, HttpStatus.OK);
  }

  // READ (GET ONE)
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
    Customer foundCustomer = customerService.getCustomer(id);
    return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
    Customer updatedCustomer = customerService.updateCustomer(id, customer);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

class TemplateCustomer{
  private String firstName;
  private String lastName;
  private String email;
  private String contactNo;
  private int yearOfBirth;

    public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getContactNo() {
    return contactNo;
  }
  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }
  public int getYearOfBirth() {
    return yearOfBirth;
  }
  public void setYearOfBirth(int yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
  }

}
