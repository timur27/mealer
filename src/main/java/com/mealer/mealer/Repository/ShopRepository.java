package com.mealer.mealer.Repository;


import com.mealer.mealer.Model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Shop findByAddress(String address);
}

