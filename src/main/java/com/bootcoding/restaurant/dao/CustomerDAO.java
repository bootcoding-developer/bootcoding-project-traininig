package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Customer;

import java.sql.*;

public class CustomerDAO {

    public static final String TABLE_NAME = "app_customer";

    private DAOService daoService;

    public CustomerDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertCustomer(Customer customer) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            if (!daoService.exists(con, TABLE_NAME, customer.getCustomerId())) {
                String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, customer.getCustomerId());
                ps.setString(2, customer.getName());
                ps.setString(3, customer.getAddress());
                ps.setLong(4, customer.getPhoneNumber());
                ps.setString(5, customer.getCity());
                ps.setString(6, customer.getState());
                ps.setString(7, customer.getEmailId());
                ps.executeUpdate();
                System.out.println(customer.getName() + " as a customer has been registered successfully, CustomerId: " + customer.getCustomerId());
            } else {
                System.out.println(customer.getCustomerId() + " already exists!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createTable() {
        Connection con = null;
        try {
            con = daoService.getConnection();

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

            System.out.println("Customer Table Query : " + query);
            stmt.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Customer getCustomerById(long customerId) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from " + TABLE_NAME + " where id = " + customerId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setPhoneNumber(rs.getLong("phone_number"));
                customer.setEmailId(rs.getString("email_id"));
                return customer;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
