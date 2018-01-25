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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductHasShop> productHasShops;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, double count, String unit) {
        this.name = name;
        this.count = count;
        Unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public Set<Dish> getDish() {
        return dish;
    }

    public void setDish(Set<Dish> dish) {
        this.dish = dish;
    }

    public Set<ProductHasShop> getProductHasShops() {
        return productHasShops;
    }

    public void setProductHasShops(Set<ProductHasShop> productHasShops) {
        this.productHasShops = productHasShops;
    }
}
