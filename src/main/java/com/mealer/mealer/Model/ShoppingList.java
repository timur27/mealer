package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name="shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy = "shoppingList")
    private Set<ElementList> elementList;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;


}
