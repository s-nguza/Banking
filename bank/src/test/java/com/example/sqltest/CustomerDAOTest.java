package com.example.sqltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Sql.DatabaseInitializer;
import com.example.Sql.CustomerDAO;
import com.example.Sql.AccountDAO;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerDAOTest {

    private static final String DB_URL = "jdbc:sqlite:banking_application.db";

    @Before
    public void setUp() {
        DatabaseInitializer.createTables();
    }

    @Test
    public void testAddCustomer() throws SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.addCustomer("John", "Doe", 30, "john.doe@example.com", "123-456-7890");

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Query the Customers table to see if the data was inserted
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers WHERE name='John' AND surname='Doe'");
            assertTrue("Customer should be inserted", rs.next());
            assertEquals("John", rs.getString("name"));
            assertEquals("Doe", rs.getString("surname"));
        }
    }

    @Test
    public void testAddAccount() throws SQLException {
        // First add a customer
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.addCustomer("Jane", "Doe", 25, "jane.doe@example.com", "987-654-3210");

        // Add an account for the customer
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.AccountDetails(1, "Savings", 5000.00);

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Query the Accounts table to see if the data was inserted
            ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE customer_id=1 AND account_type='Savings'");
            assertTrue("Account should be inserted", rs.next());
            assertEquals(5000.00, rs.getDouble("balance"), 0.01);
        }
    }

    @After
    public void tearDown() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Clean up the tables after each test
            stmt.execute("DROP TABLE IF EXISTS Customers");
            stmt.execute("DROP TABLE IF EXISTS Accounts");
            stmt.execute("DROP TABLE IF EXISTS Transactions");
        }
    }
}
