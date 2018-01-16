package com.mealer.mealer.Model;
import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    private int ID;
    private String Nick;
    private String Email;
    private String Password;
    @ManyToOne
    @JoinColumn(name = "type_user_id")
    private TypeUser typeUser;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<ShoppingList> shoppingList;

    public Users() {
    }

    public Users(String nick, String email, String password) {
        Nick = nick;
        Email = email;
        Password = password;
    }

    public Users(String nick, String email, String password, TypeUser typeUser) {
        Nick = nick;
        Email = email;
        Password = password;
        this.typeUser = typeUser;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
