package lk.jiat.ee.jersey.model;


import jakarta.enterprise.context.ApplicationScoped;
import lk.jiat.ee.jersey.annotation.UserBind;

@ApplicationScoped
public class User {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
