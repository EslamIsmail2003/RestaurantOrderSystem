package repository;

import model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepo {
    private static final Logger logger = LoggerFactory.getLogger(MenuItemRepo.class);

    public void insertMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO menu_items(item_id,name,category,price,created_at)VALUES(?,?,?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, menuItem.getId());
            stmt.setString(2, menuItem.getName());
            stmt.setString(3, menuItem.getCategory());
            stmt.setDouble(4, menuItem.getPrice());
            stmt.setTimestamp(5, menuItem.getCreatedAt());
            stmt.executeUpdate();
            System.out.println("Menu Item has been added to database! ");
        } catch (SQLException e) {
            logger.error("An error has occurred while adding menu item to the database! ", e);
            throw new RuntimeException("An error has occurred while adding menu item to the database! " + e);
        }
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                menuItems.add(rowMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching data from menu items! ", e);
            return new ArrayList<>();
        }
        return menuItems;
    }

    public List<MenuItem> getMenuItemByCategory(String category) {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu_items WHERE category = ? ";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, category);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                menuItems.add(rowMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while fetching data from menu items! ", e);
            return new ArrayList<>();
        }
        return menuItems;
    }

    private MenuItem rowMap(ResultSet resultSet)
            throws SQLException {
        return new MenuItem(
                resultSet.getString("item_id"),
                resultSet.getTimestamp("created_at"),
                resultSet.getString("name"),
                resultSet.getString("category"),
                resultSet.getDouble("price"));
    }
}

