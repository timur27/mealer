package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductHasShop> productHasShops;

    public Shop(){};

    public Shop(String name, String address){
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ProductHasShop> getProductHasShops() {
        return productHasShops;
    }

    public void setProductHasShops(Set<ProductHasShop> productHasShops) {
        this.productHasShops = productHasShops;
    }
}
