package com.mealer.mealer.Repository;

import com.mealer.mealer.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findByName(String name);

}

