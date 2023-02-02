package com.example.project.service;

import com.example.project.model.Product;
import com.example.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        Product newProduct = new Product();

        if(product.getProduct_name() != null
        && product.getProduct_type() != null
        && product.getProduct_amount() != null
        && product.getProduct_price() != null
        && product.getProduct_desc() != null)
        {
            newProduct.setProduct_name(product.getProduct_name());
            newProduct.setProduct_type(product.getProduct_type());
            newProduct.setProduct_amount(product.getProduct_amount());
            newProduct.setProduct_price(product.getProduct_price());
            newProduct.setProduct_desc(product.getProduct_desc());
        }
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }


}
