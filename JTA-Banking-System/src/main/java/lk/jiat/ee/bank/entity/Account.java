package lk.jiat.ee.bank.entity;

import jakarta.persistence.*;

@Entity

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNo;
    private double balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
