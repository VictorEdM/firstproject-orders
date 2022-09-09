package com.firstproject.orders.config;


import com.firstproject.orders.entities.User;
import com.firstproject.orders.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
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

        userRepository.saveAll(Arrays.asList(maria, alex));
    }
}
