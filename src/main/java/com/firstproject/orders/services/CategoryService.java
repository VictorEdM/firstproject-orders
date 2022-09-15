package com.firstproject.orders.services;

import com.firstproject.orders.entities.Category;
import com.firstproject.orders.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
