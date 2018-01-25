package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Status {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private Set<ElementList> elementList;

    public Status() {
    }

    public Status(String name) {
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

    public Set<ElementList> getElementList() {
        return elementList;
    }

    public void setElementList(Set<ElementList> elementList) {
        this.elementList = elementList;
    }
}
