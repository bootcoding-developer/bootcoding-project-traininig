package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDAO {

    public static final String TABLE_NAME = "app_customer";

    private DAOService daoService;

    public CustomerDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertCustomer(Customer customer) {
        try {
            Connection con = daoService.getConnection();
            if(!exists(con, customer.getCustomerId())) {
                String sql = "INSERT INTO " + TABLE_NAME
                        + " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, customer.getCustomerId());
                ps.setString(2, customer.getName());

                ps.setString(3, customer.getAddress());
                ps.setLong(4, customer.getPhoneNumber());
                ps.setString(5, customer.getCity());
                ps.setString(6, customer.getState());
                ps.setString(7, customer.getEmailId());
                ps.executeUpdate();
                con.close();
                System.out.println(customer.getCustomerId() + " inserted into DB!");
            }else{
                System.out.println(customer.getCustomerId() + " already exists!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean exists(Connection con, long id){
        try{
            Statement stmt = con.createStatement();
            String sql = "Select * from " + TABLE_NAME
                    + " where id = " + id;
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public void createTable() {
        try {

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
                    + " CONSTRAINT app_customer_pk PRIMARY KEY (id))";

            System.out.println("Create Table Query : " + query);
            stmt.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
