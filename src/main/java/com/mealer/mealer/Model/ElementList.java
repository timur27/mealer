package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name="element_list")
public class ElementList {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "element_list_has_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "element_id",
                    referencedColumnName = "id"))
    private Set<ShoppingList> shoppingList;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
