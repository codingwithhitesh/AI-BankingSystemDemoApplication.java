package com.hitesh.BankingSystemDemoAI;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Saves all sub-accounts in one table
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public  class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String type;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public BankAccount() {}

    public BankAccount(User user,String type) {
        this.user = user;
        this.type = type;
        this.balance = 0.0;
    }
    public BankAccount(User user) {
        this.user = user;
        this.balance = 0.0;
    }


    // Common Banking Operations
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (this.balance < amount) throw new IllegalStateException("Insufficient funds");
        this.balance -= amount;
    }

    // Getters and Setters
    public Long getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public User getUser() {
        return user;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }
}