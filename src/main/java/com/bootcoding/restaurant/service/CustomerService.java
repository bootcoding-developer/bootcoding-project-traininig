package com.bootcoding.restaurant.service;

import com.bootcoding.restaurant.dao.CustomerDAO;
import com.bootcoding.restaurant.model.Customer;
import com.bootcoding.restaurant.utils.AddressGenerator;
import com.bootcoding.restaurant.utils.EmailIdGenerator;
import com.bootcoding.restaurant.utils.NameGenerator;
import com.bootcoding.restaurant.utils.PhoneNumberGenerator;

public class CustomerService {

    private CustomerDAO customerDAO;
    private long BEGIN_VALUE = 100;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void createDummyCustomers() {
        for (int i = 1; i <= 500; i++) {
            Customer customer = new Customer();
            customer.setCustomerId(BEGIN_VALUE + i);
            customer.setName(NameGenerator.getName());
            customer.setAddress(AddressGenerator.getAddress());
            customer.setEmailId(EmailIdGenerator.
                    getEmailId(customer.getName()));
            customer.setCity("Nagpur");
            customer.setState("Maharashtra");
            customer.setPhoneNumber(PhoneNumberGenerator.getPhoneNumber());

            customerDAO.insertCustomer(customer);

        }
    }

    public Customer findById(long customerId) {
        return customerDAO.getCustomerById(customerId);
    }

    public void createTable() {
        customerDAO.createTable();
    }
}
