package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ntu.edu.group5.ecommerce.entity.Customer;
import com.ntu.edu.group5.ecommerce.exception.CustomerNotFoundException;
import com.ntu.edu.group5.ecommerce.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public ArrayList<Customer> searchCustomers(String firstName) {
        List<Customer> foundCustomers = customerRepository.findByFirstName(firstName);
        return (ArrayList<Customer>) foundCustomers;
    }

    @Override
    public Customer createCustomer(String firstName, String lastName,
                                    String email, String contactNo,
                                    int YOB) {

        Customer newCustomer = new Customer(firstName,lastName,email,contactNo,YOB);
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    @Override
    public Customer getCustomer(Long id) {
        // Optional<Customer> optionalCustomer = customerRepository.findById(id);
        // if(optionalCustomer.isPresent()) {
        // Customer foundCustomer = optionalCustomer.get();
        // return foundCustomer;
        // }
        // throw new CustomerNotFoundException(id);
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // retrieve the customer from the database
        // [Activity 1 - Refactor code]
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        // update the customer retrieved from the database
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
        customerToUpdate.setCustomerCart(customer.getCustomerCart());
        // save the updated customer back to the database
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
