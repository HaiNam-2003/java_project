package com.example.java_project.service;

import com.example.java_project.model.Product;
import com.example.java_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public void deleteProductById(long id) {
        productRepo.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepo.findById(id);
    }

    public List<Product> getAllProductByCategoryId(int id) {
        return productRepo.findAllByCategory_id(id);
    }
}
