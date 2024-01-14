package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.config.CustomRepositoryImplementationDetector;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ntu.edu.group5.ecommerce.entity.Address;
import com.ntu.edu.group5.ecommerce.entity.Customer;
import com.ntu.edu.group5.ecommerce.repository.AddressRepository;
import com.ntu.edu.group5.ecommerce.repository.CustomerRepository;

@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);


    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ArrayList<Address> searchAddressesByCity(String city) {
        List<Address> foundAddresses = addressRepository.findByCity(city);
        return (ArrayList<Address>) foundAddresses;
    }

    @Override
    public Address createAddress(Address address) {
        Address newAddress = addressRepository.save(address);
        return newAddress;
    }

    //create a new Address and set a customer
    public Address createAddressByCustomerId(long customerId ,String blockNumber, String streetName,
                                            String buildingName , String city, String state, String postalCode ){
        Customer foundCustomer = null;
        logger.info("🏠🔵 AddressServiceImpl - received creat request  customerId " + customerId + " blockNumber " + " streetName " +
            streetName + " buildingName " + buildingName + " city " + city + " state " + state + " postalCode " + postalCode);
        try {
            foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Cannot find customerId " + customerId));
            logger.info("🏠🔵 foundCustomer " + foundCustomer.toString());
        }catch (RuntimeException e){
             logger.info(" 🏠🔴 error finding foundCustomer " + foundCustomer + " with error message " + e);
        }
        Address newAddress = new Address(blockNumber,streetName,buildingName,city,state,postalCode);
        newAddress.setCustomer(foundCustomer);

        try {
            addressRepository.save(newAddress);
            logger.info("🏠🟢 POST newAddress saved in orderRepo " + newAddress);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("🏠🔴 Error saving newAddress", e);
            throw new RuntimeException("Error saving newAddress",e);
        }

        return newAddress;
    }

    @Override
    public ArrayList<Address> getAllAddresses() {
        ArrayList<Address> foundAllAddress = null;
        logger.info("🏠🔵 AddressServiceImpl - finding foundAllAddress ...");
        try {
            foundAllAddress = (ArrayList<Address>)addressRepository.findAll();
            logger.info("🏠🟢  foundAllAddress" + foundAllAddress);
        } catch (RuntimeException e){
            logger.error("🏠🔴 Error cannot find foundAllAddress " + e);
        }

        return foundAllAddress;
    }

    @Override
    public void deleteAddress(Long id) {
        logger.info("🏠🔵 - AddressServiceImpl - deleting foundAddresses ...");
        addressRepository.deleteById(id);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        if (addressRepository.existsById(id)) {
            address.setId(id);
            return addressRepository.save(address);
        }
        return null; // or throw an exception indicating that the address with the given id doesn't exist
    }

    @Override
    public Address getAddress(Long id) {
        logger.info("🏠🔵 AddressServiceImpl - finding address id ..." + id );
        return addressRepository.findById(id).orElse(null);

        /*
         * 14:20:40.143 [http-nio-8080-exec-8] WARN  o.h.e.jdbc.spi.SqlExceptionHelper - SQL Error: 0, SQLState: 23503
            14:20:40.143 [http-nio-8080-exec-8] ERROR o.h.e.jdbc.spi.SqlExceptionHelper - ERROR: update or delete on table "address" violates foreign key constraint "fkqqw5cd6q594ac1ifjxbq1cian" on table "orders"
            Detail: Key (id)=(1) is still referenced from table "orders".
         */
    }
}
