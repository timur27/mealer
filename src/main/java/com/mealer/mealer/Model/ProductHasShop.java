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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }
}
