package com.firstproject.orders.config;


import com.firstproject.orders.entities.*;
import com.firstproject.orders.entities.enums.OrderStatus;
import com.firstproject.orders.repositories.*;
import org.aspectj.weaver.ast.Or;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = createUser();
        List<Order> orders = createOrder(users);
        List<Product> products = createProduct();
        List<Category> categories = createCategory();
        addCategoryToProduct(products, categories);
        List<OrderItem> orderItems = createOrderItem(orders, products);
        List<Payment> payments = createPayment(orders);

    }

    public List<User> createUser() {
        User maria = new User(
                null,
                "Maria Brown",
                "maria@gmail.com",
                "867-530-369",
                "mar036**"
        );

        User alex = new User(
                null,
                "Alex Green",
                "alex@gmail.com",
                "357-445-120",
                "al035sa@"
        );

        List<User> users = Arrays.asList(maria, alex);
        userRepository.saveAll(users);
        return users;
    }

    public List<Order> createOrder(List<User> users) {
        Order mariaFirstOrder = new Order(
                null,
                Instant.parse("2019-06-20T19:53:07Z"),
                OrderStatus.PAID,
                users.get(0)
        );
        Order alexFirstOrder = new Order(
                null,
                Instant.parse("2019-07-21T03:42:10Z"),
                OrderStatus.WAITING_PAYMENT,
                users.get(1)
        );
        Order mariaSecondOrder = new Order(
                null,
                Instant.parse("2019-07-22T15:21:22Z"),
                OrderStatus.WAITING_PAYMENT,
                users.get(0)
        );

        List<Order> orders = Arrays.asList(mariaFirstOrder, alexFirstOrder, mariaSecondOrder);
        orderRepository.saveAll(orders);
        return orders;
    }

    public List<Category> createCategory() {
        Category eletronics = new Category(null, "Eletronics");
        Category books = new Category(null, "Books");
        Category computers = new Category(null, "Computers");

        List<Category> categories = Arrays.asList(eletronics, books, computers);
        categoryRepository.saveAll(categories);
        return categories;
    }

    public List<Product> createProduct() {
        Product lotr = new Product(
                null,
                "The Lord Of The Rings",
                "Frodo and Sam goes on an adventure to save the middle earth from the dark forces of Sauron",
                BigDecimal.valueOf(90.5),
                ""
        );
        Product smartTv = new Product(
                null,
                "Smart TV",
                "Smart television",
                BigDecimal.valueOf(2190.0),
                ""
        );
        Product macbookPro = new Product(
                null,
                "Macbook Pro",
                "Apple laptop",
                BigDecimal.valueOf(1250.0),
                ""
        );
        Product pcGamer = new Product(
                null,
                "PC Gamer",
                "Computer for playing games",
                BigDecimal.valueOf(1200.0),
                ""
        );
        Product railsForDummies = new Product(
                null,
                "Rails For Dummies",
                "Introductory book about Ruby on Rails",
                BigDecimal.valueOf(100.99),
                ""
        );

        List<Product> products = Arrays.asList(lotr, smartTv, macbookPro, pcGamer, railsForDummies);
        productRepository.saveAll(products);
        return products;
    }

    public void addCategoryToProduct(List<Product> products, List<Category> categories) {
        Product lotr = products.stream()
                .filter(product -> product.getName().equals("The Lord Of The Rings"))
                .findFirst().orElse(null);
        Product smartTV = products.stream()
                .filter(product -> product.getName().equals("Smart TV"))
                .findFirst().orElse(null);
        Product macBook = products.stream()
                .filter(product -> product.getName().equals("Macbook Pro"))
                .findFirst().orElse(null);
        Product pcGamer = products.stream()
                .filter(product -> product.getName().equals("PC Gamer"))
                .findFirst().orElse(null);
        Product railsForDummies = products.stream()
                .filter(product -> product.getName().equals("Rails For Dummies"))
                .findFirst().orElse(null);

        Category eletronics = categories.stream()
                .filter(category -> category.getName().equals("Eletronics"))
                .findFirst().orElse(null);
        Category books = categories.stream()
                .filter(category -> category.getName().equals("Books"))
                .findFirst().orElse(null);
        Category computers = categories.stream()
                .filter(category -> category.getName().equals("Computers"))
                .findFirst().orElse(null);

        lotr.getCategories().add(books);
        smartTV.getCategories().add(eletronics);
        smartTV.getCategories().add(computers);
        macBook.getCategories().add(computers);
        pcGamer.getCategories().add(computers);
        railsForDummies.getCategories().add(books);

        productRepository.saveAll(Arrays.asList(lotr, smartTV, macBook, pcGamer, railsForDummies));
    }

    public List<OrderItem> createOrderItem(List<Order> orders, List<Product> products) {
        OrderItem orderItem1 = new OrderItem(
                orders.get(0),
                products.get(0),
                2,
                products.get(0).getPrice()
        );
        OrderItem orderItem2 = new OrderItem(
                orders.get(0),
                products.get(2),
                1,
                products.get(2).getPrice()
        );
        OrderItem orderItem3 = new OrderItem(
                orders.get(1),
                products.get(2),
                2,
                products.get(2).getPrice()
        );
        OrderItem orderItem4 = new OrderItem(
                orders.get(2),
                products.get(4),
                2,
                products.get(4).getPrice()
        );

        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4);
        orderItemRepository.saveAll(orderItems);

        return orderItems;
    }

    public List<Payment> createPayment(List<Order> orders) {
        Payment payment1 = new Payment(
                null,
                orders.get(0).getMoment().plusSeconds(60 * 60 * 2),
                orders.get(0)
        );
        orders.get(0).setPayment(payment1);

        List<Payment> payments = Arrays.asList(payment1);
        orderRepository.saveAll(orders);
        return payments;
    }
}
