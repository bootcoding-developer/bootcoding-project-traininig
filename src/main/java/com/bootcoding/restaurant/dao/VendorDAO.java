package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Customer;
import com.bootcoding.restaurant.model.Vendor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class VendorDAO {

    public static final String TABLE_NAME = "app_vendor";
    private DAOService daoService;

    public VendorDAO(){
        // Inside Constructor
        daoService = new DAOService();
    }


    public void insertVendor(Vendor vendor) {
        try {
            Connection con = daoService.getConnection();
            if(!daoService.exists(con, TABLE_NAME, vendor.getVendorId())) {
                String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, vendor.getVendorId());
                ps.setString(2, vendor.getName());
                ps.setString(3, vendor.getAddress());
                ps.setLong(4, vendor.getPhoneNumber());
                ps.setString(5, vendor.getCity());
                ps.setString(6, vendor.getState());
                ps.setString(7, vendor.getEmailId());
                ps.setString(8, vendor.getCategory());
                ps.executeUpdate();
                System.out.println("Vendor Id " + vendor.getVendorId() + " is inserted into DB!");
            }else{
                System.out.println("Vendor Id " + vendor.getVendorId() + " already exist!");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            con.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
