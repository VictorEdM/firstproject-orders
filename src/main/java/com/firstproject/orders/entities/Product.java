package com.firstproject.orders.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;

    @Transient
    @ManyToMany
    @JoinColumn(name = "category_id")
    @Setter(AccessLevel.NONE)
    private Set<Category> categories = new HashSet<>();


    public Product(Long id, String name, String description, BigDecimal price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
