package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.CustomerDAO;
import com.bootcoding.restaurant.dao.VendorDAO;
import com.bootcoding.restaurant.model.Customer;
import com.bootcoding.restaurant.model.Vendor;
import com.bootcoding.restaurant.utils.*;

public class VendorService {

    private VendorDAO vendorDAO;

    public VendorService(){
        vendorDAO = new VendorDAO();
    }

    public void createVendors(){
        for(int i = 0; i < 100; i++){
            Vendor vendor = new Vendor();
            vendor.setVendorId(i + 1);
            vendor.setName(RestaurantNameCategoryGenerator.getName());
            vendor.setAddress(AddressGenerator.getAddress());
            vendor.setEmailId(EmailIdGenerator.
                    getEmailId(vendor.getName()));
            vendor.setCity("Nagpur");
            vendor.setState("Maharashtra");
            vendor.setPhoneNumber(PhoneNumberGenerator.getPhoneNumber());
            vendor.setCategory(RestaurantNameCategoryGenerator.getCategory(vendor.getName()));
            vendorDAO.insertVendor(vendor);
        }
    }

    public void createTable() {
        vendorDAO.createTable();
    }
}
