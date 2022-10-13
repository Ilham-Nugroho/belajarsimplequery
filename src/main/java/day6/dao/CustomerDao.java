package day6.dao;

import day6.domain.Customer;
import day6.domain.CustomerException;
import day6.service.CustomerService;
import day6.util.DbUtil;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements CustomerService {
    private Connection connection;
    private final String queryDisplayAllCustomer = "SELECT * FROM customers";
    private final String queryFindCustomerById = "SELECT * FROM customers WHERE customerID = ?";
    private final String queryEditCustomer = "UPDATE customers " + "SET firstName = ?, lastName = ?, dob = ? " + "WHERE customerID = ?";
    private final String queryDeleteCustomer = "DELETE FROM customers " + "WHERE customerID = ?";
    private final String queryInsertCustomer = "INSERT INTO customers(firstName, lastName, dob) VALUES (?, ?, ?)";
    private final String getQueryFindCustomerByFirstName = "SELECT * FROM customers WHERE firstName = ?";
    public CustomerDao(){
        connection = DbUtil.getConnection();
    }
    public CustomerDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Customer> displayAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryDisplayAllCustomer);
        ){
            while (resultSet.next()){
                customers.add(new Customer(
                        resultSet.getInt("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dob").toLocalDate()
                ));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return customers;
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerException {
        try (PreparedStatement ps = connection.prepareStatement(queryInsertCustomer)){
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getDob()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerException("Failed to add new customer");
        }
    }

    @Override
    public void editCustomer(Customer customer) throws CustomerException {
        try (PreparedStatement ps = connection.prepareStatement(queryEditCustomer)){
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setDate(3, Date.valueOf(customer.getDob()));
            ps.setInt(4, customer.getCustomerID());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerException("Failed to edit customer");
        }
    }

    @Override
    public Customer findCustomerByID(int id) throws CustomerException {
        Customer customer = null;
        try (PreparedStatement ps = connection.prepareStatement((queryFindCustomerById))){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                customer = new Customer(
                        resultSet.getInt("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dob").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerException("Can't find customer with that ID");
        }
        if(customer != null){
            return customer;
        } else{
            throw new CustomerException("Customer tidak ditemukan");
        }
    }


    @Override
    public void deleteCustomer(int id) throws CustomerException {
        try (PreparedStatement ps = connection.prepareStatement(queryDeleteCustomer)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerException("Failed to delete customer");
        }
    }

    @Override
    public Customer findCustomerByFirstName(String firstName) throws CustomerException {
        Customer customer = null;
        try (PreparedStatement ps = connection.prepareStatement((getQueryFindCustomerByFirstName))){
            ps.setString(1, firstName);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                customer = new Customer(
                        resultSet.getInt("customerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dob").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerException("Failed to find customer by said ID");
        }
        if(customer != null){
            return customer;
        } else{
            throw new CustomerException("Customer tidak ditemukan");
        }
    }
}
