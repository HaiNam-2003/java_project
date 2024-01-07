package com.example.java_project.controller;

import com.example.java_project.service.CategoryService;
import com.example.java_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCate());
        model.addAttribute("products", productService.getAllProduct());

        return "shop";
    }

    // show product by category
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.getAllCate());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));

        return "shop";
    }

    // show product details
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id).get());

        return "viewProduct";
    }
}
