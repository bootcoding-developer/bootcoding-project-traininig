package com.bootcoding.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDAO {

    public static final String TABLE_NAME = "customer";

    public void createTable(){
        try{
            // 1. Load JDBC Driver
            Class.forName("org.postgresql.Driver");

            // 2. Establish Connection with your local database
            Connection con = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "postgres");
            // 3. Create Statement Object
            Statement stmt = con.createStatement();

            // 4. Execute Query (Statement)
            // TODO - Create table query
            // TODO - Change query - For demonstration we have used SELECT query
            String sql = "Select * from " + TABLE_NAME;
            ResultSet rs = stmt.executeQuery(sql);
            // 5. Traverse ResultSet (data)
            while(rs.next()){
                System.out.println("Name  = " + rs.getString("name"));
                System.out.println("Address  = " + rs.getString("address"));
                System.out.println("Phone = " + rs.getString("phone_number"));
                System.out.println("City = " + rs.getString("city"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
