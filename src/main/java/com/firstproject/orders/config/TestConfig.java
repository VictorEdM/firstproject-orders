package com.firstproject.orders.config;


import com.firstproject.orders.entities.Category;
import com.firstproject.orders.entities.Order;
import com.firstproject.orders.entities.Product;
import com.firstproject.orders.entities.User;
import com.firstproject.orders.entities.enums.OrderStatus;
import com.firstproject.orders.repositories.CategoryRepository;
import com.firstproject.orders.repositories.OrderRepository;
import com.firstproject.orders.repositories.ProductRepository;
import com.firstproject.orders.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public void run(String... args) throws Exception {
        List<User> users = createUser();
        List<Order> orders = createOrder(users);
        List<Product> products = createProduct();
        List<Category> categories = createCategory();

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

    public List<Order> createOrder(@NotNull List<User> users) {
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
}
