package lk.jiat.ee.model;

import java.util.Set;

public class User {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public User(String password, Set<String> roles) {
        this.password = password;
        this.roles = roles;
    }

    private String password;
    private Set<String> roles;

}
