package com.firstproject.orders.repositories;

import com.firstproject.orders.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
