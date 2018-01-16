package com.mealer.mealer.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Coordinates {
    @Id
    @GeneratedValue
    private int id;
    private int x;
    private int y;
    @OneToMany(mappedBy = "coordinates", cascade = CascadeType.ALL)
    private Set<Shop> shops;
}
