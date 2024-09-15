package com.example.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    private static final String DB_URL = "jdbc:sqlite:banking_application.db";

    public void addTransaction(int accountId, String transactionType, double amount, String transactionDate) {
        String sql = "INSERT INTO Transactions (account_id, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            pstmt.setString(2, transactionType);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, transactionDate);
            pstmt.executeUpdate();
            System.out.println("Transaction added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
