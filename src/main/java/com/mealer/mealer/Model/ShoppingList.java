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

    public Set<ElementList> getElementList() {
        return elementList;
    }

    public void setElementList(Set<ElementList> elementList) {
        this.elementList = elementList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
