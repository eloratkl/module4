package com.ntu.edu.group5.ecommerce.controller;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntu.edu.group5.ecommerce.service.OrderServiceImpl;
import com.ntu.edu.group5.ecommerce.entity.Order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderServiceImpl orderServ;

    public OrderController(OrderServiceImpl orderServ) {
        this.orderServ = orderServ;
    }

    // Create new order
    // @PostMapping
    // public ResponseEntity<Order> createOrder(@RequestBody TemplateOrder
    // dataOrder) {
    // Order newOrder =
    // orderServ.createOrder(dataOrder.getCustomerId(),dataOrder.getcartItemId());
    // System.out.println("🔵 POST-CONTROLLER 🔵 newOrder " + newOrder);
    // return new ResponseEntity<>(newOrder, HttpStatus.OK);
    // }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody TemplateOrder dataOrder) {
        Order newOrder = orderServ.createOrder(dataOrder.getaddressId());
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Order>> getAllOrders() {
        ArrayList<Order> ordersList = orderServ.getAllOrders();
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable long orderId) {
        Order foundOrder = orderServ.getOrder(orderId);
        return new ResponseEntity<>(foundOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> setOrder(@PathVariable long orderId, @RequestBody TemplateOrder dataOrder) {
        Order setOrder = orderServ.setOrder(orderId, dataOrder.getaddressId(), dataOrder.getcartItemId());
        return new ResponseEntity<>(setOrder, HttpStatus.OK);
        /*
         * { "addressId":1, "productId":2, "cartItemId":5}
         */
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(@PathVariable long orderId) {
        Order delOrder = orderServ.deleteOrder(orderId);
        return new ResponseEntity<>(delOrder, HttpStatus.OK);
    }

}

class TemplateOrder {

    long addressId;
    long cartItemId;
    long productId;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    int quantity;

    public long getaddressId() {
        return addressId;
    }

    public void setaddressId(long addressId) {
        this.addressId = addressId;
    }

    public long getcartItemId() {
        return cartItemId;
    }

    public void setcartItemId(long productId) {
        this.cartItemId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
