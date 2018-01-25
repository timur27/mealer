package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name="element_list")
public class ElementList {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(mappedBy = "elementList")
    private Set<ShoppingList> shoppingList;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public ElementList() {
        shoppingList=new HashSet<ShoppingList>();
    }

    public ElementList(String name, Set<ShoppingList> shoppingList) {
        this.name = name;
        this.shoppingList = shoppingList;
    }

    public ElementList(String name) {
        this.name = name;
    }

    public ElementList(String name, Set<ShoppingList> shoppingList, Status status) {
        this.name = name;
        this.shoppingList = shoppingList;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
