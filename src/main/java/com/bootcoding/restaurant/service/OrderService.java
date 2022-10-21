package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.OrderDAO;
import com.bootcoding.restaurant.dao.VendorDAO;
import com.bootcoding.restaurant.model.Order;
import com.bootcoding.restaurant.model.Vendor;
import com.bootcoding.restaurant.utils.*;

import java.util.Date;
import java.util.Random;

public class OrderService {

    private OrderDAO orderDAO;

    public OrderService(){
        orderDAO = new OrderDAO();
    }

    public void placeOrders(){
        for(int i = 0; i < 100; i++){
            Order order = new Order();
            order.setOrderId(i + 1);
            order.setVendor(VendorBuilder.build());
            order.setCustomer(CustomerBuilder.build());
            order.setOrderDate(new Date());
            order.setOrderStatus(OrderStatusGenerator.getStatus());
            order.setDeliveryAddress(order.getCustomer().getAddress());
//            order.setTotalAmount();
            orderDAO.insertOrder(order);
        }
    }


    public void createTable() {
        orderDAO.createTable();
    }
}
