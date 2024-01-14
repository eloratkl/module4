package com.ntu.edu.group5.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToOne //original
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    //Jian add in here 231212 --->
    @JsonBackReference
    @ManyToOne(optional = false) //original
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    //end                     <---

    @Column(name = "cartItem_quantity")
    private int cartItemQuantity;

    @JsonBackReference
    @ManyToOne//original
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public CartItem(int cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

}
