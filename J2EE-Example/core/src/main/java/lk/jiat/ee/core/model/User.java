package lk.jiat.ee.core.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.FindByEmail", query = "select u from User u where u.email=:email"),
        @NamedQuery(name = "User.FindByEmailAndPassword", query = "select u from User u where u.email=:email and u.password=:password"),
//        @NamedQuery(name = "User.FindByEmail", query = "select u from User u where u.email= ?1"),

})

@Cacheable(false)
public class User implements Serializable {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }


    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String contact;
    @Column(unique = true)
    private String email;
    private String verificationCode;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;

    public User() {
    }

    public User(String name, String contact, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }
}
