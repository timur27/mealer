package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double count;
    private String Unit;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dish_has_product", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
    private Set<Dish> dish;
    @OneToMany(mappedBy = "product")
    private Set<ProductHasShop> productHasShops;

}
