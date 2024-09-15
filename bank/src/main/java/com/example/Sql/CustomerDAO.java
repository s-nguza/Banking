package com.example.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    private static final String DB_URL = "jdbc:sqlite:banking_application.db";

    public void addCustomer(String name, String surname, int age, String email, String phone) {
        String sql = "INSERT INTO Customers (name, surname, age, email, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setInt(3, age);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
