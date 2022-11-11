package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.VendorDAO;
import com.bootcoding.restaurant.model.Vendor;
import com.bootcoding.restaurant.utils.AddressGenerator;
import com.bootcoding.restaurant.utils.EmailIdGenerator;
import com.bootcoding.restaurant.utils.PhoneNumberGenerator;
import com.bootcoding.restaurant.utils.RestaurantCategoryGenerator;

public class VendorService {

    private VendorDAO vendorDAO;
    private VendorMenuItemService menuItemService;
    private long BEGIN_VALUE = 200;

    public VendorService() {
        vendorDAO = new VendorDAO();
        menuItemService = new VendorMenuItemService();
        menuItemService.createTable();
    }

    public void createVendors() {
        for (int i = 1; i <= 200; i++) {
            Vendor vendor = new Vendor();
            vendor.setVendorId(BEGIN_VALUE + i);
            vendor.setName(RestaurantCategoryGenerator.getName());
            vendor.setAddress(AddressGenerator.getAddress());
            vendor.setEmailId(EmailIdGenerator.
                    getEmailId(vendor.getName()));
            vendor.setCity("Nagpur");
            vendor.setState("Maharashtra");
            vendor.setPhoneNumber(PhoneNumberGenerator.getPhoneNumber());
            vendor.setCategory(RestaurantCategoryGenerator.getCategory(vendor.getName()));

            vendorDAO.insertVendor(vendor);

            addMenuItemsOfVendor(vendor);
        }
    }

    public void addMenuItemsOfVendor(Vendor vendor) {
        menuItemService.createMenuItems(vendor);
    }

    public void createTable() {
        vendorDAO.createTable();
    }

    public Vendor findById(long vendorId) {
        return vendorDAO.getVendorById(vendorId);
    }
}
