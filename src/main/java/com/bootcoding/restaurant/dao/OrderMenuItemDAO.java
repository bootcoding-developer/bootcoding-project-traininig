package com.bootcoding.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OrderMenuItemDAO {
    public static final String TABLE_NAME = "app_order_menu_item";

    private DAOService daoService;
    public OrderMenuItemDAO(){
        // Inside Constructor
        daoService = new DAOService();
    }

    public void createTable(){
        try{
            Connection con = daoService.getConnection();
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + " ( id bigint NOT NULL, "
                    + " order_id bigint ,"
                    + " quantity int4, "
                    + " menu_item_name text, "
                    + " is_veg bool , "
                    + " total_price numeric, "
                    + " CONSTRAINT app_order_menu_item_pk PRIMARY KEY (id))";

            System.out.println("Create Table Query : " + query);
            stmt.executeUpdate(query);


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
