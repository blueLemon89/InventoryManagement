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

import java.util.List;

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
        return "product.html";
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
        return "redirect:/product.html";
    }

    /**
     *  A REST response (HTTP status)
     */
    @PostMapping(value= "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String>  addProduct(@RequestBody Product product){
            productService.saveProduct(product);
            if(product.getProduct_name().isEmpty()
            || product.getProduct_type().isEmpty()
            || product.getProduct_amount().toString().isEmpty()
            || product.getProduct_price().toString().isEmpty()
            || product.getProduct_desc().isEmpty()){
              return new ResponseEntity<>("Create new product failed", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Create new product successful!", HttpStatus.CREATED);
    }

    @GetMapping("/edit.html/{id}")
    public String editProductForm(@PathVariable Integer id, Model model){
        model.addAttribute("products", productService.getProductById(id));
        return "edit_product.html";
    }

    @PostMapping(value = "/product.html/{id}")
    public String updateProduct(@PathVariable Integer id,
                              @ModelAttribute("products") Product product,
                              Model model){
        Product productFromDatabase = productService.getProductById(id);
        if(productFromDatabase == null) {
            return "Product not found";
        }
        productFromDatabase.setProduct_id(id);
        productFromDatabase.setProduct_name(product.getProduct_name());
        productFromDatabase.setProduct_type(product.getProduct_type());
        productFromDatabase.setProduct_amount(product.getProduct_amount());
        productFromDatabase.setProduct_price(product.getProduct_price());
        productFromDatabase.setProduct_desc(product.getProduct_desc());

        productService.updateProduct(productFromDatabase);

        return "redirect:/product.html";

    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
        return "redirect:/product.html";
    }

    @GetMapping("/product.html/search")
    public String searchProduct(Product product, Model model,@RequestParam String keyWord){
        List<Product> productList;
        if(keyWord != null){
            productList = productService.getProductsByKeyWord(keyWord);
        }
        else{
            productList = productService.getAllProduct();
        }
        model.addAttribute("products", productList);
        return "product.html";
    }
}
