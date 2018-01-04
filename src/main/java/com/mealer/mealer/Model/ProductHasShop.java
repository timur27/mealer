package com.mealer.mealer.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductHasShop implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private double prize;

}
