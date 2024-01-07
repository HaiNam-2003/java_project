package com.example.java_project.dto;

import com.example.java_project.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private int categoryId;
    private String name;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
