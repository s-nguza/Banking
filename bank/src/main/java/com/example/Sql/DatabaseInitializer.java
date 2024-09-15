package com.example.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String DB_URL = "jdbc:sqlite:banking_application.db";

    public static void createTables() {
        // SQL to create Customers table
        String createCustomersTable = "CREATE TABLE IF NOT EXISTS Customers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "surname TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "email TEXT NOT NULL UNIQUE, " + 
                "password TEXT NOT NULL);"; // Added password field
    
        // SQL to create Accounts table
        String createAccountsTable = "CREATE TABLE IF NOT EXISTS Accounts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_id INTEGER NOT NULL, " +
                "account_type TEXT NOT NULL, " +
                "balance REAL NOT NULL, " +
                "FOREIGN KEY (customer_id) REFERENCES Customers(id));";
    
        // SQL to create Transactions table
        String createTransactionsTable = "CREATE TABLE IF NOT EXISTS Transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "account_id INTEGER NOT NULL, " +
                "transaction_type TEXT NOT NULL, " +
                "amount REAL NOT NULL, " +
                "transaction_date TEXT NOT NULL, " +
                "FOREIGN KEY (account_id) REFERENCES Accounts(id));";
    
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
    
            // // Drop the Customers table if it already exists
            // stmt.execute("DROP TABLE IF EXISTS Customers;");
    
            // Create the Customers table
            stmt.execute(createCustomersTable); 
    
            // // Drop the Accounts table if it already exists (optional)
            // stmt.execute("DROP TABLE IF EXISTS Accounts;");
            // Create the Accounts table
            stmt.execute(createAccountsTable);
    
            // Drop the Transactions table if it already exists (optional)
            // stmt.execute("DROP TABLE IF EXISTS Transactions;");
            // Create the Transactions table
            stmt.execute(createTransactionsTable);
    
            System.out.println("Tables created successfully.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}