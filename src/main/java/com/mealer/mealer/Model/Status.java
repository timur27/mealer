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
}
