package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Order;

import java.sql.*;
import java.util.List;

public class OrderDAO {


    public static final String TABLE_NAME = "app_order";

    private DAOService daoService;

    public OrderDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertOrder(Order order) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            if (!daoService.exists(con, TABLE_NAME, order.getOrderId())) {
                String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, order.getOrderId());
                ps.setLong(2, order.getVendor().getVendorId());
                ps.setLong(3, order.getCustomer().getCustomerId());
                ps.setDouble(4, order.getTotalAmount());
                ps.setTimestamp(5, new Timestamp(order.getOrderDate().getTime()));
                ps.setString(6, order.getOrderStatus());
                ps.setString(7, order.getDeliveryAddress());
                ps.setString(8, order.getVendor().getCategory());
                ps.executeUpdate();
                System.out.println("Order has been placed successfully, OrderId:" + order.getOrderId());
            } else {
                System.out.println("Order Id " + order.getOrderId() + " already exists!");
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


    public void insertOrderInBulk(List<Order> orders) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            for(Order order : orders) {
                if (!daoService.exists(con, TABLE_NAME, order.getOrderId())) {
                    String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setLong(1, order.getOrderId());
                    ps.setLong(2, order.getVendor().getVendorId());
                    ps.setLong(3, order.getCustomer().getCustomerId());
                    ps.setDouble(4, order.getTotalAmount());
                    ps.setTimestamp(5, new Timestamp(order.getOrderDate().getTime()));
                    ps.setString(6, order.getOrderStatus());
                    ps.setString(7, order.getDeliveryAddress());
                    ps.setString(8, order.getVendor().getCategory());
                    ps.executeUpdate();
                    System.out.println("Order has been placed successfully, OrderId:" + order.getOrderId());
                } else {
                    System.out.println("Order Id " + order.getOrderId() + " already exists!");
                }
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
                    + " vendor_id bigint ,"
                    + " customer_id bigint, "
                    + " total_amount decimal, "
                    + " order_date timestamp , "
                    + " status text, "
                    + " delivery_address text, "
                    + " category text, "
                    + " CONSTRAINT app_order_pk PRIMARY KEY (id))";
            System.out.println("Order Table Query : " + query);
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
}
