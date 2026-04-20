package repository;

import model.Customer;
import model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OrderRepo {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepo.class);

    public void insertOrder(Order order) {
        String sql = "INSERT INTO orders(order_id, customer_id, status, total_amount, created_at) VALUES(?,?,?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, order.getId());
            stmt.setString(2, order.getCustomerId());
            stmt.setString(3, order.getStatus());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setTimestamp(5, order.getCreatedAt());
            stmt.executeUpdate();
            System.out.println("Order added to database! ");
        } catch (SQLException e) {
            logger.error("An error occurred while adding order to database! ", e);
            throw new RuntimeException("An error has occurred while adding order to database! " + e);
        }
    }

    public void deleteOrder(String orderId, String email) {
        String sql = "UPDATE orders SET status = 'Cancelled' WHERE order_id =? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, orderId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    logger.debug("Attempting to cancel order with id: '{}'", orderId);
                    System.out.println("No matching order found (wrong email or order id).");
                }
        } catch (SQLException e) {
            logger.error("Error cancelling order!", e);
        }
    }

    public HashMap<String, Integer> getOrderCountByStatus(){
        HashMap<String, Integer> mapList = new LinkedHashMap<>();
        String sql = "SELECT COUNT(*) AS total_orders, status FROM orders GROUP BY status";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                String status = resultSet.getString("status");
                int itemCount = resultSet.getInt("total_orders");
                mapList.put(status,itemCount);
            }
        } catch (SQLException e) {
           logger.error("An error occurred while fetching total orders! ", e);
           return new HashMap<>();
        }
        return mapList;
    }



    public double getTotalRevenue() {
        String sql = "SELECT SUM(total_amount) AS total_revenue FROM orders WHERE status = 'completed'";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching total revenue!", e);
        }
        return 0.0;
    }


    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from orders! ", e);
            return new ArrayList<>();
        }
        return orders;
    }

    public List<Order> getOrderByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status =?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from orders! ", e);
            return new ArrayList<>();
        }
        return orders;
    }

    public void updateOrderByStatus(String orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id =? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, orderId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                logger.warn("Error while updating orders {}", orderId);
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while updating order status! ", e);
        }
    }


    public List<Order> getOrderByEmail(String email) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, o.customer_id, o.status, o.total_amount, o.created_at FROM orders O JOIN customers C ON o.customer_id = c.customer_id WHERE c.email = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from customers! ", e);
        }
        return orders;
    }

    public List<Order> getOrderByCustomerId(String customer_id) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, customer_id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from orders! ", e);
            return new ArrayList<>();
        }
        return orders;
    }

    public List<Order> getOrderByOrderId(String orderId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE order_id = ? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orderId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orders.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from orders! ", e);
            return new ArrayList<>();
        }
        return orders;
    }

    private Order mapRow(ResultSet resultSet)
            throws SQLException {
        return new Order(
                resultSet.getString("order_id"),
                resultSet.getTimestamp("created_at"),
                resultSet.getString("customer_id"),
                resultSet.getString("status"),
                resultSet.getDouble("total_amount"));
    }
}

