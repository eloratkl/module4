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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name of product is mandatory")
    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @Size(min = 3, max = 50, message = "Description needs to have at least 3 characters and at most 50")
    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private Category category;

    @Column(name = "status")
    private Status status;

    @DecimalMin("0.10") // Product must cost at least 10cents and above
    @Column(name = "price")
    private double price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;



    public Product(String name, int quantity, String description, Category category, Status status, double price,
            String manufacturer) {
        this.name = name;
        this.quantity = quantity; // the amount that sellers have
        this.description = description;
        this.category = category;
        this.status = status;
        this.price = price;
        this.manufacturer = manufacturer;
    }

}
