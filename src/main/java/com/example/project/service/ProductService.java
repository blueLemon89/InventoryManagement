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

    public void saveProduct(Product product) {
        Product newProduct = new Product();

        if(!product.getProduct_name().isEmpty()
        && !product.getProduct_type().isEmpty()
        && !product.getProduct_amount().toString().isEmpty()
        && !product.getProduct_price().toString().isEmpty()
        && !product.getProduct_desc().isEmpty())
        {
            newProduct.setProduct_name(product.getProduct_name());
            newProduct.setProduct_type(product.getProduct_type());
            newProduct.setProduct_amount(product.getProduct_amount());
            newProduct.setProduct_price(product.getProduct_price());
            newProduct.setProduct_desc(product.getProduct_desc());
        }
        productRepository.save(newProduct);
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).get();

    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }


    public List<Product> getProductsByKeyWord(String keyWord){
        return productRepository.findByKeyWord(keyWord);
    }

}
