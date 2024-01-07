package com.example.java_project.controller;


import com.example.java_project.dto.ProductDTO;
import com.example.java_project.model.Category;
import com.example.java_project.model.Product;
import com.example.java_project.service.CategoryService;
import com.example.java_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Categories Section //
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCate(Model model) {
        model.addAttribute("categories", categoryService.getAllCate());
        return "categories";
    }


    @GetMapping("/admin/categories/add")
    public String Categories(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    // add
    @PostMapping("/admin/categories/add")
    public String addCategories(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    //delete
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCate(@PathVariable("id") int id) {
        categoryService.deleteCat(id);
        return "redirect:/admin/categories";
    }

    // update
    @GetMapping("/admin/categories/update/{id}")
    public String updateCate(@PathVariable("id") int id, Model model) {
        Optional<Category> category = categoryService.getCateById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404 NOT FOUND";
        }
    }

    // Product sections //

    // show list product
    @GetMapping("/admin/products")
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    // page product add
    @GetMapping("/admin/products/add")
    public String products(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCate());
        return "productsAdd";
    }

    // add product
    @PostMapping("/admin/products/add")
    public String addProducts(@ModelAttribute("productDTO") ProductDTO productDTO,
                              @RequestParam("productImage") MultipartFile file,
                              @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCateById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if (!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            try (FileOutputStream fos = new FileOutputStream(uploadDir + "/" + imageUUID)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCate());
        model.addAttribute("productDTO", productDTO);

        return "productsAdd";
    }
}
