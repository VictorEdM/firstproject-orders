package com.firstproject.orders.services;

import com.firstproject.orders.entities.Category;
import com.firstproject.orders.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Long id) {
        return repository.findById(id).get();
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
