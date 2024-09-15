package com.example.sqltest;

import org.junit.Before;
import org.junit.Test;

import com.example.Sql.DatabaseInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;

public class DatabaseInitializerTest {

    private static final String DB_URL = "jdbc:sqlite:banking_application.db";

    @Before
    public void setUp() {
        // This runs before each test. You can use it to recreate the tables.
        DatabaseInitializer.createTables();
    }

    @Test
    public void testCustomersTableCreated() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Execute a query to check if the Customers table exists
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Customers'");
            assertTrue("Customers table should exist", rs.next());
        }
    }

    @Test
    public void testAccountsTableCreated() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Execute a query to check if the Accounts table exists
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Accounts'");
            assertTrue("Accounts table should exist", rs.next());
        }
    }

    @Test
    public void testTransactionsTableCreated() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Execute a query to check if the Transactions table exists
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Transactions'");
            assertTrue("Transactions table should exist", rs.next());
        }
    }
}
