package com.ntu.edu.group5.ecommerce.entity;

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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    // [Activity 2 - validation]
    @Digits(fraction = 0, integer = 8, message = "Contact no should be 8 digits")
    @Column(name = "contact_no")
    private String contactNo;

    // [Activity 2 - validation]
    @Range(min = 1940, max = 2005, message = "Year Of Birth should be between 1940 and 2005")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart customerCart;

    //231214 - customer links to orders as well as customer
    @OneToMany(mappedBy = "orderingCustomer", cascade = CascadeType.ALL)
    private List<Order> orderList;

    //231214 - link customer to address
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addressList;

    public Customer(@NotBlank(message = "First name is mandatory") String firstName, String lastName,
            @Email(message = "Email should be valid") String email,
            @Digits(fraction = 0, integer = 8, message = "Contact no should be 8 digits") String contactNo,
            @Range(min = 1940, max = 2005, message = "Year Of Birth should be between 1940 and 2005") int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.yearOfBirth = yearOfBirth;
    }

    
}
