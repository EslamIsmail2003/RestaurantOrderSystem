package repository;

import model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepo {
    private static final Logger logger = LoggerFactory.getLogger(OrderItemRepo.class);

    public void insertOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items(order_item_id, order_id, menu_item_id, quantity, price, created_at) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, orderItem.getId());
            stmt.setString(2, orderItem.getOrderId());
            stmt.setString(3, orderItem.getMenuItemId());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setDouble(5, orderItem.getPrice());
            stmt.setTimestamp(6, orderItem.getCreatedAt());
            stmt.executeUpdate();
            System.out.println("Order item has been added to database! ");
        } catch (SQLException e) {
            logger.error("An error has occurred while adding order item to database! ", e);
            throw new RuntimeException("An error has occurred while adding order item to database! " + e);
        }
    }

    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orderItems.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from order items! ", e);
            return new ArrayList<>();
        }
        return orderItems;
    }

    public List<OrderItem> getOrderByOrderId(String OrderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, OrderId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                orderItems.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from order items! ", e);
            return new ArrayList<>();
        }
        return orderItems;
    }

    private OrderItem mapRow(ResultSet resultSet)
            throws SQLException {
        return new OrderItem(
                resultSet.getString("order_item_id"),
                resultSet.getTimestamp("created_at"),
                resultSet.getString("order_id"),
                resultSet.getString("menu_item_id"),
                resultSet.getInt("quantity"),
                resultSet.getDouble("price"));
    }
}
