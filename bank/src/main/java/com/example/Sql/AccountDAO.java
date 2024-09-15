package com.example.Sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AccountDAO {
    private static final String DB_URL =  "jdbc:sqlite:banking_application.db";

    public void AccountDetails(int customer_id,String account_type ,double balance){
        String Sql = "INSERT INTO Accounts(customer_id,account_type,balance) VALUES(?,?,?)";

        try (Connection con = DriverManager.getConnection(DB_URL);
            PreparedStatement ps = con.prepareStatement(Sql)) {
                ps.setInt(1, customer_id);
                ps.setString(2, Sql);
                ps.setDouble(3, balance);

                ps.executeUpdate();
                System.out.println("Accoumt added successfully");


            }catch (SQLException e){
                e.printStackTrace();

            }



    }


}
