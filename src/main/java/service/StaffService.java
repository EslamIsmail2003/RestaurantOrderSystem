package service;

import model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.StaffRepo;
import utils.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);
    private static final StaffRepo staffRepo = new StaffRepo();

    public void addStaff() {
        String firstName = Utils.getValidInputs("Enter staff's first name: ", " Invalid first name format! ");
        String lastName = Utils.getValidInputs("Enter staff's last name: ", " Invalid last name format! ");
        String role = Utils.getValidInputs("Enter staff's role: ", " Invalid role format! ");
        String email = Utils.getValidatedEmail("Enter staff's email: ");
        Staff newStaff = addStaff(firstName, lastName, role, email);
        staffRepo.insertStaff(newStaff);
    }

    public void getAllStaff() {
        List<Staff> staffList = staffRepo.getAllStaff();
        for (Staff staff : staffList) {
            staff.displayStaff();
        }
    }

    public void getStaffByEmail() {
        String email = Utils.getValidatedEmail("Enter staff's email: ");
        List<Staff> staffList = staffRepo.getStaffByEmail(email);
        if (staffList.isEmpty()) {
            logger.warn("No staff were found with this email {}", email);
            System.out.println("No staff were found for this email! ");
        }
        for (Staff staff : staffList) {
            staff.displayStaff();
        }
    }

    public void getStaffByRole() {
        String role = Utils.getValidInputs("Enter staff's role: ", " Invalid role format! ");
        List<Staff> staffList = staffRepo.getStaffByRole(role);
        if (staffList.isEmpty()) {
            logger.warn("The role doesn't have staff yet! {} ", role);
            System.out.println("This role doesn't have any added staff yet! ");
        }
        for (Staff staff : staffList) {
            staff.displayStaff();
        }
    }

    private static Staff addStaff(String firstName, String lastName, String role, String email) {
        String newUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new Staff(newUUID, createdAt, firstName, lastName, role, email);
    }
}
