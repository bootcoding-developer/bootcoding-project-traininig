package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.MenuItemDAO;
import com.bootcoding.restaurant.model.MenuItem;
import com.bootcoding.restaurant.model.Vendor;
import com.bootcoding.restaurant.utils.MenuItemGenerator;
import com.bootcoding.restaurant.utils.MenuPriceGenerator;
import com.bootcoding.restaurant.utils.QuantityGenerator;

public class VendorMenuItemService {

    private MenuItemDAO menuItemDAO;

    public VendorMenuItemService() {
        menuItemDAO = new MenuItemDAO();
    }

    public void createMenuItems(Vendor vendor) {
        for (int i = 1; i <= QuantityGenerator.getQuantity(5,10); i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setMenuItemId(vendor.getVendorId() * 100 + i);
            menuItem.setVendorId(vendor.getVendorId());
            menuItem.setCategory(vendor.getCategory());
            menuItem.setMenuItem(MenuItemGenerator.getMenuItemByVendorCategory(vendor.getCategory()));
            menuItem.setVeg(true);
            menuItem.setPrice(MenuPriceGenerator.getPrice());
            menuItemDAO.insertMenuItem(menuItem);
        }
    }

    public void createTable() {
        menuItemDAO.createTable();
    }
}
