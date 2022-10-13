package day6.dao;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import day6.domain.Customer;
import day6.domain.CustomerException;
import day6.util.DbUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {
    private static CustomerDao dao;

    @BeforeEach
    void setUp() {
        Connection connection = DbUtil.getConnection();
        dao = new CustomerDao(connection);
    }

    @Test
    void displayAllCustomers() {
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer("Maudy", "Ayunda", LocalDate.of(1994, 12, 19));
        try {
            dao.addCustomer(customer);
            Customer result = dao.findCustomerByFirstName("Maudy");
            Assertions.assertEquals("Maudy", result.getFirstName());
        }catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void editCustomer() {
        Customer customer = new Customer(1, "Riny", "Howk", LocalDate.of(1968, 05, 12));
        try {
            dao.editCustomer(customer);
            Customer result = dao.findCustomerByID(1);
            Assertions.assertEquals("Riny", result.getFirstName());
        }catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void findCustomerById() {
//       try {
//           dao.findCustomerByID(13);
//           Customer result = dao.findCustomerByID(13);
//           Assertions.assertEquals(13, result.getCustomerID());
//       } catch (CustomerException e){
//           e.getMessage();
//       }
        Exception e = Assertions.assertThrows(CustomerException.class, ()-> dao.findCustomerByID(100));
        Assertions.assertEquals("Customer tidak ditemukan", e.getMessage());
    }

    @Test
    void deleteCustomer() {
        try {
            dao.deleteCustomer(2);
            Exception e = Assertions.assertThrows(CustomerException.class, () -> dao.findCustomerByID(2));
            Assertions.assertEquals("Customer tidak ditemukan", e.getMessage());
        } catch (CustomerException e){
            e.printStackTrace();
        }
    }

    @Test
    void findCustomerByFirstName() {
        try {
            dao.findCustomerByFirstName("Bao");
            Customer result = dao.findCustomerByFirstName("Bao");
            Assertions.assertEquals("Bao", result.getFirstName());
        } catch (CustomerException e){
            e.printStackTrace();
        }
    }
}