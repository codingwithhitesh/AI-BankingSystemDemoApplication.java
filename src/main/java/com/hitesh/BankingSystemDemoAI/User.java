package com.hitesh.BankingSystemDemoAI;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String homeCity;

    // A user can have multiple bank accounts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BankAccount> accounts;



    // Constructors, Getters, and Setters
    public User() {}

    public User(String name) {
        this.name = name;
    }
    public Long getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public List<BankAccount> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public void changeHomeCity(String newHomeCity) {
        if (newHomeCity == null || newHomeCity.trim().isEmpty()) {
            throw new IllegalArgumentException("Home city name cannot be blank");
        }
        this.homeCity = newHomeCity;
    }
}