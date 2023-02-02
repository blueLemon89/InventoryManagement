package com.example.project.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id",strategy = "increment")
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
