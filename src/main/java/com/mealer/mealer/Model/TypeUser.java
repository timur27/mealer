package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "type_user")
public class TypeUser {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "typeUser", cascade = CascadeType.ALL)
    private Set<ShoppingList> shoppingList;

    public TypeUser() {
    }

    public TypeUser(String name) {
        this.name = name;
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

    public Set<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
