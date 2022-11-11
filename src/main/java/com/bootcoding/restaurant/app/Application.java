package com.bootcoding.restaurant.app;

import com.bootcoding.restaurant.service.OrderService;
import com.bootcoding.restaurant.service.VendorService;

public class Application {

    public static void main(String[] args) {

//        CustomerService cs = new CustomerService();
//        cs.createTable();
//        cs.createDummyCustomers();

//        VendorService vendorService = new VendorService();
//        vendorService.createTable();
//        vendorService.createVendors();

        OrderService orderService = new OrderService();
        orderService.createTable();
        orderService.placeOrders();
    }


}
