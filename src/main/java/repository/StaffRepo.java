package repository;

import model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffRepo {
    private static final Logger logger = LoggerFactory.getLogger(StaffRepo.class);

    public void insertStaff(Staff staff) {
        String sql = "INSERT INTO staff(staff_id, first_name, last_name, role, email, created_at) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, staff.getId());
            stmt.setString(2, staff.getFirstName());
            stmt.setString(3, staff.getLastName());
            stmt.setString(4, staff.getRole());
            stmt.setString(5, staff.getEmail());
            stmt.setTimestamp(6, staff.getCreatedAt());
            stmt.executeUpdate();
            System.out.println("Staff has been added to database! ");
        } catch (SQLException e) {
            logger.error("An error has occurred while adding staff to database! ", e);
            throw new RuntimeException("An error has occurred while adding staff to database! " + e);
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                staffList.add(rowMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from staff! ", e);
            return new ArrayList<>();
        }
        return staffList;
    }

    public List<Staff> getStaffByRole(String role) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE role = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, role);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                staffList.add(rowMap(resultSet));
            }
        } catch (SQLException e) {
            logger.error("An error has occurred while fetching data from staff! ", e);
            return new ArrayList<>();
        }
        return staffList;
    }

    private Staff rowMap(ResultSet resultSet) throws SQLException {
        return new Staff(
                resultSet.getString("staff_id"),
                resultSet.getTimestamp("created_at"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("role"),
                resultSet.getString("email"));
    }
}
