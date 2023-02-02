package com.example.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "product_id")
    @GenericGenerator(name = "product_id",strategy = "increment")
    private Integer product_id;
    @NonNull
    private String product_name;

    @NonNull
    private String product_type;
    @NonNull
    private Integer product_price;

    @NonNull
    private Integer product_amount;

    @NonNull
    private String product_desc;

}
