package com.example.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String DB_URL = "jdbc:sqlite:your_database_name.db";

    public static void createTables() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS Users (" +
                                  "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  "username TEXT NOT NULL, " +
                                  "email TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            System.out.println("Users table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
