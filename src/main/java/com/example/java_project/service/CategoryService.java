package com.example.java_project.service;

import com.example.java_project.model.Category;
import com.example.java_project.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCate() {
        return categoryRepo.findAll();
    }

    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    public void deleteCat(int id) {
        categoryRepo.deleteById(id);
    }

    public Optional<Category> getCateById(int id) {
        return categoryRepo.findById(id);
    }
}
