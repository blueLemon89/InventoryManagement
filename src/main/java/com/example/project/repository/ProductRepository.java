package com.example.project.repository;

import com.example.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM products p where p.category_id =:id", nativeQuery = true)
    public List<Product> getProductsByCategoryId(Integer id);

//    @Modifying
//    @Query(value = "update products p set p.product_name =:product.product_name, p.product_type =:product.product_type," +
//            "p.product_amount =: product.product_amount, p.product_price=:product.product_price, p.product_desc =: product.product_desc where p.product_id =:product.product_id", nativeQuery = true)
//    public Product editProduct(Product product);


    @Query(value = "select * from products p where p.product_name like %:keyWord% or p.product_type like %:keyWord%" +
            " or p.product_desc like %:keyWord%", nativeQuery = true)
    List<Product> findByKeyWord(@Param("keyWord") String keyWord);
}