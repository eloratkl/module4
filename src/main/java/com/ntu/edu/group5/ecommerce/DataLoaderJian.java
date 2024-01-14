package com.ntu.edu.group5.ecommerce;

import org.springframework.stereotype.Component;

import com.ntu.edu.group5.ecommerce.entity.*;

import com.ntu.edu.group5.ecommerce.repository.AddressRepository;
import com.ntu.edu.group5.ecommerce.repository.CartItemRepository;
import com.ntu.edu.group5.ecommerce.repository.CartRepository;
import com.ntu.edu.group5.ecommerce.repository.CustomerRepository;
import com.ntu.edu.group5.ecommerce.repository.OrderRepository;
import com.ntu.edu.group5.ecommerce.repository.ProductRepository;
import com.ntu.edu.group5.ecommerce.repository.SellerRepository;
import com.ntu.edu.group5.ecommerce.service.AddressServiceImpl;
import com.ntu.edu.group5.ecommerce.service.CartItemService;
import com.ntu.edu.group5.ecommerce.service.CartItemServiceImpl;
import com.ntu.edu.group5.ecommerce.service.CartServiceImpl;
import com.ntu.edu.group5.ecommerce.service.CustomerServiceImpl;
import com.ntu.edu.group5.ecommerce.service.OrderService;
import com.ntu.edu.group5.ecommerce.service.OrderServiceImpl;
import com.ntu.edu.group5.ecommerce.service.ProductServiceImpl;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoaderJian {
        private CustomerRepository customerRepository;
        private CustomerServiceImpl customerServiceImpl;
        private ProductRepository productRepository;
        private ProductServiceImpl productServiceImpl;
        private CartRepository cartRepository;
        private SellerRepository sellerRepository;
        private CartItemRepository cartItemRepository;
        private OrderRepository orderRepository;
        private OrderServiceImpl orderServiceImpl;
        private CartItemServiceImpl cartItemServiceImpl;
        private CartServiceImpl cartServiceImpl;
        private AddressRepository addressRepository;
        private AddressServiceImpl addressServiceImpl;

        public DataLoaderJian(CustomerRepository customerRepository,
                        CustomerServiceImpl customerServiceImpl,
                        ProductRepository productRepository,
                        ProductServiceImpl productServiceImpl,
                        CartRepository cartRepository,
                        SellerRepository sellerRepository,
                        CartItemRepository cartItemRepository,
                        OrderRepository orderRepository,
                        OrderServiceImpl orderServiceImpl,
                        CartItemServiceImpl cartItemServiceImpl,
                        CartServiceImpl cartServiceImpl,
                        AddressRepository addressRepository,
                        AddressServiceImpl addressServiceImpl){
                this.customerRepository = customerRepository;
                this.customerServiceImpl = customerServiceImpl;
                this.productRepository = productRepository;
                this.productServiceImpl = productServiceImpl;
                this.cartRepository = cartRepository;
                this.sellerRepository = sellerRepository;
                this.cartItemRepository = cartItemRepository;
                this.orderRepository = orderRepository;
                this.orderServiceImpl = orderServiceImpl;
                this.cartItemServiceImpl=cartItemServiceImpl;
                this.cartServiceImpl =cartServiceImpl;
                this.addressRepository = addressRepository;
                this.addressServiceImpl = addressServiceImpl;
        }

        @PostConstruct
        public void loadData() {
                // clear the database first
                customerRepository.deleteAll();
                productRepository.deleteAll();

                // load data here
                // customers 1-10
                customerServiceImpl.createCustomer("Catherine", "Tiong", "catt@gmail.com", "12345678", 1990);
                customerServiceImpl.createCustomer("Siti", "Aminah K", "sitiamk@gmail.com", "12345678", 1991);
                customerServiceImpl.createCustomer("Sariha", "Sareeha", "sariha@gmail.com", "12345678", 1992);
                customerServiceImpl.createCustomer("Sara", "Saranya", "sara@gmail.com", "12345678", 1993);
                customerServiceImpl.createCustomer("ZJ", "Lee", "zjlee@gmail.com", "12345678", 1994);
                customerServiceImpl.createCustomer("Sam", "Altman", "saltman@openai.com", "12345678", 1995);
                customerServiceImpl.createCustomer("Mark", "Zuckerberg", "zuck@fb.com", "12345678", 1996);
                customerServiceImpl.createCustomer("Linus", "Torvald", "lt@linux.com", "12345678", 1997);
                customerServiceImpl.createCustomer("Elon", "Musk", "elonm@spacex.com", "12345678", 1998);
                customerServiceImpl.createCustomer("Andrew", "Ng", "andrewng@stanford.com", "12345678", 1999);

                //address 1 - 10
                addressServiceImpl.createAddressByCustomerId(1,"15", "Jalan Tukang", "Smith Place", "SG", "SG", "670455");
                addressServiceImpl.createAddressByCustomerId(1,"20", "Jalan Bakar", "Burns", "SG", "SG", "670456");
                addressServiceImpl.createAddressByCustomerId(1,"25", "Jalan Tunang", "Engaged Building", "SG", "SG", "670456");
                addressServiceImpl.createAddressByCustomerId(2,"30", "Kampung Java", "Java Village", "SG", "SG", "670457");
                addressServiceImpl.createAddressByCustomerId(2,"35", "Jalan Satay", "Satay Street", "SG", "SG", "670458");
                addressServiceImpl.createAddressByCustomerId(3,"40", "Kay Poh Road", "Busy Body Inc", "SG", "SG", "670459");
                addressServiceImpl.createAddressByCustomerId(3,"45", "Lorong Lew Lian", "Durian Hub", "SG", "SG", "670460");
                addressServiceImpl.createAddressByCustomerId(4,"50", "Rotan Lane", "Discipline Place", "SG", "SG", "670461");
                addressServiceImpl.createAddressByCustomerId(4,"55", "Pending Road", "Wait Long Long", "SG", "SG", "670462");
                addressServiceImpl.createAddressByCustomerId(5,"55", "Jalan Malu Malu", "Shy", "SG", "SG", "670462");
                //address 11-15
                addressServiceImpl.createAddressByCustomerId(5,"60", "Jalan Langgar", "Crash Zone", "SG", "SG", "670463");
                addressServiceImpl.createAddressByCustomerId(6,"65", "Jalan Pisang", "Banana", "SG", "SG", "670464");
                addressServiceImpl.createAddressByCustomerId(7,"70", "Kallang Pudding Road", "Pudding", "SG", "SG", "670464");
                addressServiceImpl.createAddressByCustomerId(8,"75", "Sandwich Road", "Delicious", "SG", "SG", "670465");
                addressServiceImpl.createAddressByCustomerId(9,"80", "Cheow Keng Road", "Escape", "SG", "SG", "670466");
                addressServiceImpl.createAddressByCustomerId(10,"85", "Robinson Road", "Robbing son", "SG", "SG", "670467");

                //sellers creation

                sellerRepository.save(new Seller("John", "Doe", "johndoe@eshop.com", "12345678", "password!"));
                sellerRepository.save(new Seller("Bill", "Gates", "billgates@microsoft.com", "12345678", "12345678!"));
                sellerRepository.save(new Seller("Andrew", "Gates", "billgates@microsoft.com", "12345678", "12345678!"));

                // productRepository 1-10

                productServiceImpl.createProductSetSeller(1,"Macbook", 1, "Apple product",
                                Category.ELECTRONICS, Status.ACTIVE, 1900.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(1,"Macbook Pro", 1, "Apple product",
                                Category.ELECTRONICS, Status.ACTIVE, 3700.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(1,"Samsung Fold", 1, "Android Folding Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 2200.0,
                                "Somesong");
                productServiceImpl.createProductSetSeller(1,"Iphone 15 Max", 1, "Apple Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 2300.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(1,"Google Pixel", 1, "Android Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 1800.0,
                                "Googol");
                productServiceImpl.createProductSetSeller(1,"Ipad", 1, "Technological Product from Apple", Category.ELECTRONICS,
                                Status.ACTIVE, 900.99, "Apple Inc");
                productServiceImpl.createProductSetSeller(2,"Apple Pen", 2, "Technological stylus from Apple", Category.ELECTRONICS,
                                Status.ACTIVE, 98.99, "Apple Inc");
                productServiceImpl.createProductSetSeller(2,"Sumsung Galaxy watch", 1, "Technological Product from Samsung",
                                Category.ELECTRONICS, Status.ACTIVE, 499.0,"Samsung Electronics");
                productServiceImpl.createProductSetSeller(2,"Ideapad Slim 5", 1, "Laptop",
                                Category.ELECTRONICS, Status.ACTIVE, 1200.0,
                                "Lenovo");
                productServiceImpl.createProductSetSeller(2,"Surface Pro", 1, "Tablet PC",
                                Category.ELECTRONICS, Status.ACTIVE, 1600.0,
                                "MiloSoft");


                // product 11 to 20
                productServiceImpl.createProductSetSeller(2,"Ipad 9", 1, "Technological Product from Apple", Category.ELECTRONICS,
                                Status.ACTIVE, 1000.99, "Apple Inc");
                productServiceImpl.createProductSetSeller(2,"Apple Pen 5", 2, "Technological stylus from Apple", Category.ELECTRONICS,
                                Status.ACTIVE, 200.99, "Apple Inc");
                productServiceImpl.createProductSetSeller(2,"Sumsung Galaxy watch 5", 1, "Technological Product from Samsung",
                                Category.ELECTRONICS, Status.ACTIVE, 599.0,
                                "Samsung Electronics");
                productServiceImpl.createProductSetSeller(3,"Ideapad Slim 6", 1, "Laptop",
                                Category.ELECTRONICS, Status.ACTIVE, 1300.0,
                                "Lenovo");
                productServiceImpl.createProductSetSeller(3,"Surface Pro 10", 1, "Tablet PC",
                                Category.ELECTRONICS, Status.ACTIVE, 1900.0,
                                "MiloSoft");
                productServiceImpl.createProductSetSeller(3,"Macbook 10", 1, "Apple product",
                                Category.ELECTRONICS, Status.ACTIVE, 1800.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(3,"Macbook Pro 10", 1, "Apple product",
                                Category.ELECTRONICS, Status.ACTIVE, 3900.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(3,"Samsung Fold 2", 1, "Android Folding Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 2200.0,
                                "Somesong");
                productServiceImpl.createProductSetSeller(3,"Iphone 20 Max", 1, "Apple Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 2500.0,
                                "Ping Guo Inc");
                productServiceImpl.createProductSetSeller(3,"Google Pixel 10", 1, "Android Phone",
                                Category.ELECTRONICS, Status.ACTIVE, 2000.0,
                                "Googol");

                // create orders
                orderServiceImpl.createOrder(1);
                orderServiceImpl.createOrder(2);
                orderServiceImpl.createOrder(3);
                orderServiceImpl.createOrder(4);
                orderServiceImpl.createOrder(5);
                orderServiceImpl.createOrder(6);
                orderServiceImpl.createOrder(7);
                orderServiceImpl.createOrder(8);
                orderServiceImpl.createOrder(9);
                orderServiceImpl.createOrder(10);

                // IMPORTANT NOTE!!!!
                /*231212 meeting with Wilmond**** need to create Cart first, and then only create cart item !!!!!!! cart id is inside cart item ****
                Follow the hierachy - create the master first (no entity dependency), and then the slave (the one with dependency, it holds the key)
                *231313  this works!! will follow.
                */

                // cartRepository

                cartServiceImpl.createCartById(1);
                cartServiceImpl.createCartById(2);
                cartServiceImpl.createCartById(3);
                cartServiceImpl.createCartById(4);
                cartServiceImpl.createCartById(5);
                cartServiceImpl.createCartById(6);
                cartServiceImpl.createCartById(7);
                cartServiceImpl.createCartById(8);
                cartServiceImpl.createCartById(9);
                cartServiceImpl.createCartById(10);

                // cartItemRepository
                cartItemServiceImpl.createCartItem(1,1,1,11);
                cartItemServiceImpl.createCartItem(2,1,2,22);
                cartItemServiceImpl.createCartItem(3,1,3,33);
                cartItemServiceImpl.createCartItem(4,1,4,44);
                cartItemServiceImpl.createCartItem(5,2,5,55);
                cartItemServiceImpl.createCartItem(6,2,6,66);
                cartItemServiceImpl.createCartItem(7,2,7,77);
                cartItemServiceImpl.createCartItem(8,3,8,88);
                cartItemServiceImpl.createCartItem(9,3,9,99);
                cartItemServiceImpl.createCartItem(10,3,10,100);

                // cartRepository.save(new Cart(14.55));
                // cartRepository.save(new Cart(5.84));

                // SellerRepository
                // sellerRepository.save(new Seller("Sally", "Wong", "67912380"));
                // sellerRepository.save(new Seller("Martin", "Goh", "67944321"));
                // sellerRepository.save(new Seller("Daniel", "Tan", "67988457"));

        }
}
