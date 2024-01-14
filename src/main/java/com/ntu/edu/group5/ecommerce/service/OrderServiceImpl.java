package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ntu.edu.group5.ecommerce.entity.*;
import com.ntu.edu.group5.ecommerce.repository.*;



@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private AddressRepository addressRepo;
    private OrderRepository orderRepo;
    private CartItemRepository cartItemRepo;
    private ProductRepository productRepo;
    private CustomerRepository customerRepo;

    @Autowired
    public OrderServiceImpl(AddressRepository addressRepo,
                            OrderRepository orderRepo ,
                            ProductRepository productRepo,
                            CartItemRepository cartItemRepo,
                            CustomerRepository customerRepository){
        this.addressRepo = addressRepo;
        this.orderRepo = orderRepo;
        this.cartItemRepo=cartItemRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
    }


    // 231211 -1541 - working ok
    @Override
    public Order createOrder (long addressId){

        Address foundAddress = null;

        logger.info("🧾🔵 createOrder requested addressId " + addressId );
        try {
            foundAddress = addressRepo.findById(addressId)
                .orElseThrow(()-> new RuntimeException("Cannot find addressId " + addressId));
            logger.info("🧾🔵 foundAddress " + foundAddress);
        }catch (RuntimeException e){
             logger.info(" 🧾🔴 error finding foundAddress " + foundAddress + " with error message " + e);
        }
        Customer foundCustomer = foundAddress.getCustomer();

        Order newOrder = new Order();
        newOrder.setOrderAddress(foundAddress);
        newOrder.setOrderingCustomer(foundCustomer);

        // set up an empty ArrayList inside Order class for newly created order
        ArrayList<CartItem> newCartItems = new ArrayList<CartItem>();
        newOrder.setOrderedItems(newCartItems);

        try {
            orderRepo.save(newOrder);
            logger.info("🧾🟢(d) POST newOrder saved in orderRepo " + newOrder);
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("🧾🔴 Error saving order", e);
            throw new RuntimeException("Error saving order",e);
        }
        return newOrder;
    }


    //231211 - 1553 - tested working
    @Override
    public Order getOrder(long id){
        Order foundOrder = null;
        logger.info("🧾🔵 finding foundOrder ... " + id);
        try{
            foundOrder =orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found"){});
            foundOrder.getOrderAddress().getId();
            foundOrder.getOrderedItems();
        }catch (RuntimeException e){
            logger.error("🧾🔴 Error finding order" + id + "error message " + e);
        }
        logger.info("🧾🟢 Success finding foundOrder " + foundOrder.toString());
        return foundOrder;
    }

    //231211 - 1553 - tested working
    @Override
    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> getAllOrders= new ArrayList<>();
        try {
            logger.info("🧾🔵 finding getAllOrders ... ");
            getAllOrders = (ArrayList<Order>) orderRepo.findAll();
        } catch (Exception e){
            logger.error("🧾🔴 Error finding getAllOrders "+ e);
        }
        return getAllOrders;
    }
    // 231211 - 1611 - not working. Cannot find cartItem although it exist in DB. no exceptions thrown.
    // if cartItem is not in DB, will receive RuntimeException as expected.
    @Override
    public Order setOrder(long id, long addressId , long cartItemId){
        Order setOrder = null;
        logger.info("🧾🔵 setOrder request with  orderId "+ id + " addressId " + addressId + " cartItemId " + cartItemId);
        try{
             setOrder = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found"){});
            logger.info("🧾🔵 found setOrder ... "+ setOrder.toString());
        }catch (RuntimeException e){
            logger.error("🧾🔴 Error finding setOrder " + id + " error message "+ e);
        }

        Address setAddress = null;
        try {
            setAddress = addressRepo.findById(addressId).orElseThrow(()-> new RuntimeException("Address not found"){});
            logger.info("🧾🔵 found foundAddress ... "+ setAddress.toString());
        } catch (RuntimeException e){
            logger.error("🧾🔴 Error finding foundAddress " + addressId + " error message "+ e);
        }

        CartItem setCartItem = null;
        try{
            setCartItem= cartItemRepo.findById(cartItemId).orElseThrow(()-> new RuntimeException("CartItem not found"));
            logger.info("🧾🔵 found setCartItem ... "+ setCartItem.toString());
            /*
             * 16:10:35.094 [http-nio-8080-exec-1] ERROR c.n.e.g.e.service.OrderServiceImpl - 🧾🔴 Error finding setCartItem 0 
             * error message java.lang.RuntimeException: CartItem not found
             */
        } catch(RuntimeException e){
            logger.error("🧾🔴 Error finding setCartItem " + cartItemId + " error message "+ e);
        }

        setOrder.setOrderAddress(setAddress);
        List<CartItem> cartItemList = setOrder.getOrderedItems();
        cartItemList.add(setCartItem);
        setOrder.setOrderedItems(cartItemList);

        try{
            setOrder = orderRepo.save(setOrder);
            logger.info("🧾🟢 Success saving setOrder " + setOrder);
        }catch(Exception e){
            logger.error("🧾🔴 Error saving setOrder " + setOrder + " error message "+ e);
        }

        return setOrder;

    }

    //231211 - 1631 - working as expected
    @Override
    public Order deleteOrder(long id){
        Order delOrder = null;
        try{
            delOrder = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("Order not found!"){});
            orderRepo.deleteById(id);
            logger.info("🧾🟢🗑 delOrder " + id);
        }catch(RuntimeException e){
            logger.error("🧾🔴 Error saving deleting delOrder error message "+ e);
        }

        return delOrder;
    }

}
