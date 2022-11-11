package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.OrderDAO;
import com.bootcoding.restaurant.model.Customer;
import com.bootcoding.restaurant.model.Order;
import com.bootcoding.restaurant.model.OrderMenuItem;
import com.bootcoding.restaurant.model.Vendor;
import com.bootcoding.restaurant.utils.CustomerBuilder;
import com.bootcoding.restaurant.utils.DateGenerator;
import com.bootcoding.restaurant.utils.OrderStatusGenerator;
import com.bootcoding.restaurant.utils.VendorBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderService {

    private OrderDAO orderDAO;
    private OrderMenuItemService orderMenuItemService;
    private long BEGIN_VALUE = 300;

    public OrderService() {
        orderDAO = new OrderDAO();
        orderMenuItemService = new OrderMenuItemService();
        orderMenuItemService.createTable();
    }

    public void placeOrders() {
        int batchSize = 100;
        int counter = 1;
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            Order order = new Order();
            Vendor vendor = new VendorService().findById(VendorBuilder.build().getVendorId());
            Customer customer = new CustomerService().findById(CustomerBuilder.build().getCustomerId());
            if (Objects.isNull(vendor) || Objects.isNull(customer)) {
                continue;
            }
            order.setOrderId(BEGIN_VALUE + i);
            order.setVendor(vendor);
            order.setCustomer(customer);
            order.setOrderDate(DateGenerator.randomDate());
            order.setOrderStatus(OrderStatusGenerator.getStatus());
            order.setDeliveryAddress(order.getCustomer().getAddress());

            orders.add(order);
            if(counter++ >= batchSize){
                placeOrdersInBatches(orders);
                counter = 1;
                orders.clear();
            }
        }
    }

    private void placeOrdersInBatches(List<Order> orders) {
        orderMenuItemService.insertOrderMenuItemsInBulk(orders);
        addTotalPrice(orders);
        orderDAO.insertOrderInBulk(orders);
    }

    private void addTotalPrice(List<Order> orders) {
        for(Order order : orders){
            order.setTotalAmount(order.getMenuItems().stream().mapToDouble(OrderMenuItem::getPrice).sum());
        }
    }


    public void addOrderMenuItems(Order order) {
        orderMenuItemService.insertOrderMenuItems(order);
    }

    public void createTable() {
        orderDAO.createTable();
    }
}
