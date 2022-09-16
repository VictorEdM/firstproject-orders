package com.firstproject.orders.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Transient
    @ManyToMany
    @Setter(AccessLevel.NONE)
    private Set<Product> products = new HashSet<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
