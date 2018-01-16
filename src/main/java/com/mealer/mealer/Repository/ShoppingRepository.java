package com.mealer.mealer.Repository;

import com.mealer.mealer.Model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShoppingRepository extends JpaRepository<ShoppingList,Integer> {
    public List<ShoppingList> findByTypeUser(String name);

}
