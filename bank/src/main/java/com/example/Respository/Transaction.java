package com.example.Respository;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String transactionType;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDateTime transactionDate;

    // Relationship with Account
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Constructors, getters, and setters

    public Transaction() {
    }

    public Transaction(String transactionType, Double amount, LocalDateTime transactionDate, Account account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.account = account;
    }

    // Getters and Setters
}

