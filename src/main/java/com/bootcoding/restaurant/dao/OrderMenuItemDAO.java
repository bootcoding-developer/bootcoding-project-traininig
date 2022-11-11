package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.OrderMenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderMenuItemDAO {
    public static final String TABLE_NAME = "app_order_menu_item";

    private DAOService daoService;

    public OrderMenuItemDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void createTable() {
        Connection con = null;
        try {
            con = daoService.getConnection();
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + " ( id bigint NOT NULL, "
                    + " order_id bigint ,"
                    + " quantity int4, "
                    + " menu_item_name text, "
                    + " is_veg bool , "
                    + " total_price numeric, "
                    + " CONSTRAINT app_order_menu_item_pk PRIMARY KEY (id))";

//            System.out.println("Create Table Query : " + query);
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

    public void insertMenuItem(OrderMenuItem menuItem) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            long menuItemId = menuItem.getId();
            if (!daoService.exists(con, TABLE_NAME, menuItemId)) {
                String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, menuItemId);
                ps.setLong(2, menuItem.getOrderId());
                ps.setInt(3, menuItem.getQuantity());
                ps.setString(4, menuItem.getMenuItem());
                ps.setBoolean(5, menuItem.isVeg());
                ps.setDouble(6, menuItem.getPrice());
                ps.executeUpdate();
//                System.out.println("Order Menu Item - Id " + menuItemId + " is inserted into DB!");
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

    public void insertMenuItemInBulk(List<OrderMenuItem> menuItems) {
        Connection con = null;
        try {
            con = daoService.getConnection();
            for(OrderMenuItem menuItem : menuItems) {
                long menuItemId = menuItem.getId();
                if (!daoService.exists(con, TABLE_NAME, menuItemId)) {
                    String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setLong(1, menuItemId);
                    ps.setLong(2, menuItem.getOrderId());
                    ps.setInt(3, menuItem.getQuantity());
                    ps.setString(4, menuItem.getMenuItem());
                    ps.setBoolean(5, menuItem.isVeg());
                    ps.setDouble(6, menuItem.getPrice());
                    ps.executeUpdate();
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
}
