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
    @JoinColumn(name = "type_user_id")
    private TypeUser typeUser;

    public ShoppingList(String name, TypeUser typeUser){
        this.name = name;
        this.typeUser = typeUser;
    }

    public ShoppingList(){};

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

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
