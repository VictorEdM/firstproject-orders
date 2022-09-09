package com.firstproject.orders.repositories;

import com.firstproject.orders.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}