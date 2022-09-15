package com.firstproject.orders.config;


import com.firstproject.orders.entities.Order;
import com.firstproject.orders.entities.User;
import com.firstproject.orders.entities.enums.OrderStatus;
import com.firstproject.orders.repositories.OrderRepository;
import com.firstproject.orders.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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

    @Override
    public void run(String... args) throws Exception {
        List<User> users = createUser();
        List<Order> orders = createOrder(users);

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
}
