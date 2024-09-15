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
                "email TEXT NOT NULL, " +
                "phone TEXT NOT NULL);";

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

            // Execute the table creation statements
            stmt.execute(createCustomersTable);
            stmt.execute(createAccountsTable);
            stmt.execute(createTransactionsTable);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
