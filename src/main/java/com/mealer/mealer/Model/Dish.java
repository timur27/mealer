package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy = "dish")
    private Set<Product> products;
}
