package com.example.project.controller;

import com.example.project.model.Product;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product";
    }

//    @GetMapping("/products")
//    public ResponseEntity<String> listProduct(Model model){
//        model.addAttribute("products", productService.getAllProductDTO());
//        return new ResponseEntity<>("product", HttpStatus.OK);
//    }

    @GetMapping("/product/new")
    public String createProductForm( Model model){
        // create category object to hold category form data
        Product product = new Product();
        model.addAttribute("products", product);
        return "create_product";
    }
    @PostMapping("/product")
    public String saveStudent(@ModelAttribute("products") Product product) {
        productService.saveProduct(product);
        return "redirect:/product";
    }
}
