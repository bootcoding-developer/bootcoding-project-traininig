package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.Order;
import com.bootcoding.restaurant.model.Vendor;

import java.sql.*;

public class OrderDAO {


    public static final String TABLE_NAME = "app_order";

    private DAOService daoService;
    public OrderDAO(){
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertOrder(Order order) {
        try {
            Connection con = daoService.getConnection();
            if(!daoService.exists(con, TABLE_NAME, order.getOrderId())) {
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
                System.out.println("Vendor Id " + order.getOrderId() + " is inserted into DB!");
            }else{
                System.out.println("Vendor Id " + order.getOrderId() + " already exist!");
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
                    + " vendor_id bigint ,"
                    + " customer_id bigint, "
                    + " total_amount decimal, "
                    + " order_date timestamp , "
                    + " status text, "
                    + " delivery_address text, "
                    + " category text, "
                    + " CONSTRAINT app_order_pk PRIMARY KEY (id))";
            System.out.println("Create Table Query : " + query);
            stmt.executeUpdate(query);
            con.close();


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
