package com.bootcoding.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VendorDAO {

    public static final String TABLE_NAME = "app_vendor";
    private DAOService daoService;
    public VendorDAO(){
        // Inside Constructor
        daoService = new DAOService();
    }
    public void createTable(){
        try{

            Connection con = daoService.getConnection();
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + " ( id bigint NOT NULL, "
                    + " name text ,"
                    + " address text, "
                    + " phone_number bigint, "
                    + " city text , "
                    + " state text, "
                    + " email_id text, "
                    + " category text, "
                    + " CONSTRAINT app_vendor_pk PRIMARY KEY (id))";

            System.out.println("Create Table Query : " + query);
            stmt.executeUpdate(query);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
