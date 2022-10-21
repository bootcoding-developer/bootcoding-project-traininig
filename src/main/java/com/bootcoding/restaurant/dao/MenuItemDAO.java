package com.bootcoding.restaurant.dao;

import com.bootcoding.restaurant.model.MenuItem;
import com.bootcoding.restaurant.model.Vendor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class MenuItemDAO {
    public static final String TABLE_NAME = "app_menu_item";

    private DAOService daoService;

    public MenuItemDAO() {
        // Inside Constructor
        daoService = new DAOService();
    }

    public void insertMenuItem(MenuItem menuItem) {
        try {
            Connection con = daoService.getConnection();
            long menuItemId = menuItem.getMenuItemId();
            if(!daoService.exists(con, TABLE_NAME, menuItemId)) {
                String sql = "INSERT INTO " + TABLE_NAME + " VALUES ( ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, menuItemId);
                ps.setLong(2, menuItem.getVendorId());
                ps.setString(3, menuItem.getMenuItem());
                ps.setDouble(4, menuItem.getPrice());
                ps.setString(5, menuItem.getCategory());
                ps.setBoolean(6, menuItem.isVeg());
                ps.executeUpdate();
                System.out.println("Menu Item - Id " + menuItemId + " is inserted into DB!");
            }else{
                System.out.println("Menu Item - Id " + menuItemId + " already exist!");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void createTable() {
        try {
            Connection con = daoService.getConnection();

            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + " ( id bigint NOT NULL, "
                    + " vendor_id bigint ,"
                    + " menu_item_name text, "
                    + " price decimal, "
                    + " category text, "
                    + " is_veg bool , "
                    + " CONSTRAINT app_menu_item_pk PRIMARY KEY (id))";

            System.out.println("Create Table Query : " + query);
            stmt.executeUpdate(query);
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
