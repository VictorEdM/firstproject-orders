package com.firstproject.orders.controllers;

import com.firstproject.orders.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> findAll() {
        User user = new User(
                1L,
                "Steve Jenkins",
                "steve@gmail.com",
                "536-447-256",
                "steve326JK"
        );
        return ResponseEntity.ok().body(user);
    }

}
