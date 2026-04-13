package repository;

import model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepo.class);
    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customers(customer_id, first_name, last_name, email, phone, city, created_at) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getCity());
            stmt.setTimestamp(7, customer.getCreatedAt());
            stmt.executeUpdate();
            System.out.println("Customer added to database! ");
        } catch (SQLException e) {
            logger.error("An error occurred while adding customer to database! ", e);
            throw new RuntimeException("An error occurred while adding customer to database! " + e);
        }
    }

    public List<Customer> getCustomerByEmail(String email) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE email = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                customers.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching data from customers! ", e);
        }
        return customers;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                customers.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching data from customers! ", e);
            return new ArrayList<>();
        }
        return customers;
    }

    public List<Customer> getCustomerByCity(String city){
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE city = ? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,city);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                customers.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching data from customers! ", e);
            return new ArrayList<>();
        }
        return customers;
    }

    private Customer mapRow(ResultSet resultSet)
            throws SQLException {
        return new Customer(
                resultSet.getString("customer_id"),
                resultSet.getTimestamp("created_at"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("city"));
    }
}

