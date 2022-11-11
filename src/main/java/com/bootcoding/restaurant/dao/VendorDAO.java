package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Vendor;

import java.sql.*;

public class VendorDAO {

    public static final String TABLE_NAME = "app_vendor";
    private DAOService daoService;

    public VendorDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertVendor(Vendor vendor) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            if (!daoService.exists(con, TABLE_NAME, vendor.getVendorId())) {
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
                System.out.println(vendor.getName() + " restaurant has been registered successfully, RestaurantId: " + vendor.getVendorId());
            } else {
                System.out.println("Vendor Id " + vendor.getVendorId() + " already exists!");
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
                    + " category text, "
                    + " CONSTRAINT app_vendor_pk PRIMARY KEY (id))";

            System.out.println("Create Table Query : " + query);
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

    public Vendor getVendorById(long vendorId) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from " + TABLE_NAME + " where id = " + vendorId;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Vendor vendor = new Vendor();
                vendor.setVendorId(rs.getLong("id"));
                vendor.setName(rs.getString("name"));
                vendor.setCategory(rs.getString("category"));
                vendor.setCity(rs.getString("city"));
                vendor.setState(rs.getString("state"));
                vendor.setPhoneNumber(rs.getLong("phone_number"));
                vendor.setEmailId(rs.getString("email_id"));
                return vendor;
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
