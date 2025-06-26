package lk.jiat.ee.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findByEmail",query = "SELECT u FROM User u WHERE u.email=:email"),
        @NamedQuery(name = "User.findByEmailAndPassword",query = "SELECT u FROM User u WHERE u.email=:email AND u.password=:password"),
})
@Cacheable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private String email;

    @OneToMany(mappedBy ="user" )
    private List<Account> accounts = new ArrayList<Account>();


    public User( String name, String email ,String password) {

        this.name = name;
        this.email = email;
        this.password = password;

    }

    public User(){}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
