package com.mealer.mealer.Repository;

import com.mealer.mealer.Model.ShoppingList;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingRepository extends CrudRepository<ShoppingList,Integer> {

}
