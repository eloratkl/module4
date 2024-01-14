package com.ntu.edu.group5.ecommerce.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ntu.edu.group5.ecommerce.entity.Cart;
import com.ntu.edu.group5.ecommerce.entity.CartItem;
import com.ntu.edu.group5.ecommerce.entity.Order;
import com.ntu.edu.group5.ecommerce.entity.Product;
import com.ntu.edu.group5.ecommerce.exception.CartItemNotFoundException;
import com.ntu.edu.group5.ecommerce.repository.CartItemRepository;

import com.ntu.edu.group5.ecommerce.repository.*;

@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                                ProductRepository productRepository,
                                OrderRepository orderRepository,
                                CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderRepository =orderRepository;
        this.cartRepository=cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        CartItem newCartItem=null;
        try{
            newCartItem = cartItemRepository.save(cartItem);
            logger.info("🛒🟢  newCart set with product, quantity and order saved to repo" + newCartItem.toString());
        }
        catch(Exception e){
            logger.error("🛒🔴 Error saving  " + newCartItem + " error " + e);
        }
        return newCartItem;
    }

    // 231213 method for populating initial DB !!
    public CartItem createCartItem(long productId , long orderId, long cartId, int quantity){
        CartItem newCartItem = new CartItem();
         newCartItem.setCartItemQuantity(quantity);
        Product foundProduct = null;
        try {
            foundProduct =  productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found! "+ productId){});
            logger.info("🛒 foundProduct" + foundProduct.toString());
            }
        catch(RuntimeException e){
            logger.error("🛒🔴 Error cannot find productId " + productId + " error " + e);
        }
        newCartItem.setProduct(foundProduct);
        Order foundOrder = null;
        try {
            foundOrder = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found! "+ orderId){});
            logger.info("🛒 foundOrder" + foundOrder.toString());
        }
        catch(RuntimeException e){
            logger.error("🛒🔴 Error cannot find orderId " + orderId + " error " + e);
        }
        newCartItem.setOrder(foundOrder);

        Cart foundCart = null;
        try{
            foundCart = cartRepository.findById(cartId)
                .orElseThrow(()-> new RuntimeException("Cart not found! "+ cartId){});
        }catch(RuntimeException e){
            logger.error("🛒🔴 Error cannot find foundCart " + cartId + " error " + e);
        }
        newCartItem.setCart(foundCart);

        checkThenUpdateOrderPrice(foundOrder,foundProduct.getPrice(),quantity);

        try{
            //saving the newCartItem into DB
            newCartItem = cartItemRepository.save(newCartItem);
            logger.info("🛒🟢  newCart set with product, quantity and order saved to repo"
                + newCartItem.toString());
        }catch (Exception e){
            logger.error("🛒🔴 Error saving  " + newCartItem + " error " + e);
        }
        return newCartItem;
    }

    public void checkThenUpdateOrderPrice(Order order, double newCartItemPrice, int newCartItemQuantity){
        double checkTotal = 0.0;
        int number = 1;
        // check existing Order total price
        for (int i=0; i < order.getOrderedItems().size() ; i ++){
            CartItem currCartItem = order.getOrderedItems().get(i);
            double unitPrice = currCartItem.getProduct().getPrice();
            int cartItemQuantity = currCartItem.getCartItemQuantity();
            double subTotal = unitPrice*cartItemQuantity ;

            logger.info("🔵💲checking  currCartItem " + number + " unitPrice " + unitPrice + " x cartItemQuantity " + cartItemQuantity + " equals " + subTotal);
            checkTotal+= subTotal;
            number = i +1;
        }
        number+=1;
        // check existing order Price + newCartItem total Price
        double newCartItemTotalPrice = newCartItemPrice*newCartItemQuantity;
        checkTotal +=  newCartItemTotalPrice;
        logger.info("🔵💲checking  currCartItem " + number + " unitPrice " + newCartItemPrice + " x cartItemQuantity " + newCartItemQuantity + " equals " + newCartItemTotalPrice);

        // calculated Price + new Price
        double calcTotal = order.countTotal() + newCartItemTotalPrice ;

        //check if it matches , setTotal and save to REPO if yes
        if (checkTotal == calcTotal){
            logger.info("🛒🟢💲 Checked calculation correct for Order. Saving to REPO" + order.toString());
            order.setTotal(calcTotal);
            orderRepository.save(order);
        }else {
            logger.info("🛒🔴💲  WRONG CALCULATION! ORDER NOT SAVED. checkTotal " + checkTotal + " !EQUALS " + calcTotal );
        }

    }

    @Override
    public ArrayList<CartItem> getAllCartItems() {
        ArrayList<CartItem> foundAllCartItems = null;
        try {
            logger.info("🛒🔵 finding foundAllCartItems ...");
            foundAllCartItems = (ArrayList<CartItem>)cartItemRepository.findAll();
            logger.info("🛒🟢  foundAllCartItems" + foundAllCartItems);
        } catch (RuntimeException e){
            logger.error("🛒🔴 Error cannot find foundAllCartItems " + e);
        }

        return foundAllCartItems;
    }

    @Override
    public void deleteCartItem(Long id) {
        try {
            cartItemRepository.deleteById(id);
            logger.info("🛒🟢  deleted cartItem " + id);
        } catch(Exception e){
            logger.error("🛒🔴 Could not delete cartItem" + e);
        }
    }

    @Override
    public CartItem updateCartItem(Long id, CartItem cartItem) {
        CartItem cartItemToUpdate = null;
        try {
            cartItemToUpdate = cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException(id));
            logger.info("🛒🔵 found updateCartItem" + cartItemToUpdate.toString());
        }catch(CartItemNotFoundException e){
            logger.error("🛒🔴 Error cannot find updateCartItem " + id + " error message " + e);
        }
        // update the cartItem retrieved fromn the database
        cartItemToUpdate.setCart(cartItem.getCart());
        cartItemToUpdate.setCartItemQuantity(cartItem.getCartItemQuantity());
        cartItemToUpdate.setProduct(cartItem.getProduct());
        // save the updated cartItem back to the database
        try {
            logger.info("🛒🔵 saving updateCartItem ...");
            cartItemRepository.save(cartItemToUpdate);
        }catch (Exception e){
            logger.error("🛒🔴 Error saving  " + cartItemToUpdate + " error " + e);
        }


        //TODO Experimental - to verify
        Order foundOrder = cartItem.getOrder();
        checkThenUpdateOrderPrice(foundOrder,cartItem.getProduct().getPrice(),cartItem.getCartItemQuantity());

        return cartItemToUpdate;
    }

    @Override
    public CartItem getCartItem(Long id) {
        CartItem foundCart = null;
        try {
            logger.info("🛒🔵 finding foundCart ... " + id);
            foundCart = cartItemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot find foundCart "+ id));
            logger.info("🛒 foundCart" + foundCart);
        }catch(RuntimeException e){
            logger.error("🛒🔴 Error cannot find foundCart " + id + " error message " + e);
        }
        return foundCart;
    }

}
