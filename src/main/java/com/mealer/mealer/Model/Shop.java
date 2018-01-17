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
    @ManyToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductHasShop> productHasShops;


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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Set<ProductHasShop> getProductHasShops() {
        return productHasShops;
    }

    public void setProductHasShops(Set<ProductHasShop> productHasShops) {
        this.productHasShops = productHasShops;
    }
}
