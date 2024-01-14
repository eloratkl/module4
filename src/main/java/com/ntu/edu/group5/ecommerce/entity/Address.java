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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Block number is mandatory")
    @Column(name = "block_number")
    private String blockNumber;

    @NotBlank(message = "Street name is mandatory")
    @Column(name = "street_name")
    private String streetName;

    @NotBlank(message = "Building name is mandatory")
    @Column(name = "building_name")
    private String buildingName;

    @NotBlank(message = "City is mandatory")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Column(name = "state")
    private String state;

    @Digits(integer = 6, fraction = 0, message = "Postal code should be exactly 6 digits")
    @Column(name = "postal_code")
    private String postalCode;

    //231214 -Join address to order. Note,without cascadetype.all , address does not populate in order table
    @OneToOne (mappedBy = "orderAddress", cascade =CascadeType.DETACH)
    private Order order;

    //231214 -Join address to order. Note,without cascadetype.all , address does not populate in order table
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    Customer customer;

    public Address(String blockNumber, String streetName, String buildingName, String city, String state, String postalCode) {
        this.blockNumber = blockNumber;
        this.streetName = streetName;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }


}
