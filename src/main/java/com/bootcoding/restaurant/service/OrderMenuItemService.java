package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.OrderMenuItemDAO;
import com.bootcoding.restaurant.model.Order;
import com.bootcoding.restaurant.model.OrderMenuItem;
import com.bootcoding.restaurant.utils.MenuItemGenerator;
import com.bootcoding.restaurant.utils.MenuPriceGenerator;
import com.bootcoding.restaurant.utils.QuantityGenerator;

import java.util.ArrayList;
import java.util.List;

public class OrderMenuItemService {

    private OrderMenuItemDAO menuItemDAO;

    public OrderMenuItemService() {
        menuItemDAO = new OrderMenuItemDAO();
    }

    public void insertOrderMenuItems(Order order) {
        double totalPrice = 0.0;
        for (int i = 1; i <= QuantityGenerator.getQuantity(10); i++) {
            OrderMenuItem menuItem = new OrderMenuItem();
            menuItem.setId(order.getOrderId() * 100 + i);
            menuItem.setOrderId(order.getOrderId());
            menuItem.setQuantity(QuantityGenerator.getQuantity(5));
            menuItem.setVeg(true);
            menuItem.setPrice(MenuPriceGenerator.getPrice());
            menuItem.setMenuItem(MenuItemGenerator.getMenuItemByVendor(order.getVendor().getName()));
            totalPrice += menuItem.getPrice();
            menuItemDAO.insertMenuItem(menuItem);
        }
        order.setTotalAmount(totalPrice);
    }


    public void insertOrderMenuItemsInBulk(List<Order> orders){
        List<OrderMenuItem> allOrderMenuItemsInOne = new ArrayList<>();
        for(Order order : orders){
            List<OrderMenuItem> orderMenuItems = buildOrderMenuItems(order);
            order.setMenuItems(orderMenuItems);
            allOrderMenuItemsInOne.addAll(orderMenuItems);
        }
        menuItemDAO.insertMenuItemInBulk(allOrderMenuItemsInOne);
    }

    public List<OrderMenuItem> buildOrderMenuItems(Order order){
        List<OrderMenuItem> orderMenuItems = new ArrayList<>();
        for (int i = 1; i <= QuantityGenerator.getQuantity(10); i++) {
            OrderMenuItem menuItem = new OrderMenuItem();
            menuItem.setId(order.getOrderId() * 100 + i);
            menuItem.setOrderId(order.getOrderId());
            menuItem.setQuantity(QuantityGenerator.getQuantity(5));
            menuItem.setVeg(true);
            menuItem.setPrice(MenuPriceGenerator.getPrice());
            menuItem.setMenuItem(MenuItemGenerator.getMenuItemByVendor(order.getVendor().getName()));
            orderMenuItems.add(menuItem);
        }
        return orderMenuItems;
    }

    public void createTable() {
        menuItemDAO.createTable();
    }

}
