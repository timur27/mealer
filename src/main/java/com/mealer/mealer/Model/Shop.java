package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shop {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductHasShop> productHasShops;
}
