package com.example.project.controller;

import com.example.project.model.Product;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     *  A REST response (HTTP status)
     */
    @GetMapping("/all")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<Object>(productService.getAllProduct(), HttpStatus.OK);
    }
    /**
     * A thymeleaf HTML response
     */
    @GetMapping(value = "/product.html")
    public String getProductHTML(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "product";
    }

    /**
     * A thymeleaf HTML response
     */
    @GetMapping(value = "/create.html")
    public String createProductForm( Model model){
        // create category object to hold category form data
        Product product = new Product();
        model.addAttribute("products", product);
        return "create_product";
    }
    @PostMapping("/product.html")
    public String saveProduct(@ModelAttribute("products") Product product) {
        productService.saveProduct(product);
        return "redirect:product.html";
    }

    /**
     *  A REST response (HTTP status)
     */
    @PostMapping(value= "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String>  addProduct(@RequestBody Product product){
            productService.saveProduct(product);
            if(product.getProduct_name() == null
            || product.getProduct_type() == null
            || product.getProduct_amount() == null
            || product.getProduct_price() == null
            || product.getProduct_desc() == null){
              return new ResponseEntity<>("Create new product failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Create new product successful!", HttpStatus.CREATED);
    }
}
