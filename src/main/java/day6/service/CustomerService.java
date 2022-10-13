package day6.service;

import day6.domain.Customer;
import day6.domain.CustomerException;

import java.util.List;

public interface CustomerService {
    List<Customer> displayAllCustomers();
    void addCustomer(Customer customer) throws CustomerException;
    void editCustomer(Customer customer) throws CustomerException;
    Customer findCustomerByID(int id) throws CustomerException;
    void deleteCustomer(int id) throws CustomerException;
    Customer findCustomerByFirstName(String firstName) throws  CustomerException;
}
