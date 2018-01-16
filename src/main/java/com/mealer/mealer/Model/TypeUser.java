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
    private Set<Users> users;

    public TypeUser() {
    }

    public TypeUser(String name) {
        this.name = name;
    }

    public TypeUser(String name, Set<Users> users) {
        this.name = name;
        this.users = users;
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

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
