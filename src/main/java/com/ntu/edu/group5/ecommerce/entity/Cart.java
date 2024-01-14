package com.ntu.edu.group5.ecommerce.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cart_total")
    private double cartTotal;

    // To uncomment after merge
    // 231313 - Jian changed to FetchType.EAGER to eliminate not found error when creating cartItem to Cart
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // TO-DO to implement the logic to get the cart total price from cartItem
    // Quantity * price from product
    public double getCartTotal() {
        double sum = 0;
        if (!cartItems.isEmpty()) {
            for (CartItem cartItem : cartItems) {
                sum += cartItem.getCartItemQuantity() * cartItem.getProduct().getPrice();
            }
        }

        String formattedSum = String.format("%.2f", sum);
        double totalSum = Double.parseDouble(formattedSum);
        return totalSum;
    }

}
