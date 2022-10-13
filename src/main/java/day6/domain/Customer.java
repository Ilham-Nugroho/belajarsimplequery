package day6.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public int getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerID == customer.customerID && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(dob, customer.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, firstName, lastName, dob);
    }

    public Customer(int customerID, String firstName, String lastName, LocalDate dob) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Customer(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
}
