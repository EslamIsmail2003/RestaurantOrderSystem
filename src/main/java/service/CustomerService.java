package service;

import model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.CustomerRepo;
import repository.OrderRepo;
import utils.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static final CustomerRepo customerRepo = new CustomerRepo();
    private static final OrderRepo orderRepo = new OrderRepo();

    public void registerCustomer() {
        String firstName = Utils.getValidInputs("Enter your first name: ", " Invalid first name format! ");
        String lastName = Utils.getValidInputs("Enter your last name: ", " Invalid last name format! ");
        String phone = Utils.getValidInputs("Enter your phone number: ", " Invalid phone number format! ");
        String email = getUniqueEmail();
        String city = Utils.getValidInputs("Enter your city: ", " Invalid city format! ");
        Customer newCustomer = addCustomer(firstName, lastName, phone, email, city);
        customerRepo.insertCustomer(newCustomer);
    }


    private String getUniqueEmail() {
        String email = Utils.getValidatedEmail("Enter your email: ");
        while (true) {
            List<Customer> existing = customerRepo.getCustomerByEmail(email);
            if (!existing.isEmpty()) {
                logger.warn("Email is already registered! {}", email);
                System.out.println("This email is already registered! please try again: ");
                email = Utils.getStringInput();
            } else {
                return email;
            }
        }
    }


    private static Customer addCustomer(String firstName, String lastName, String phone, String email, String city) {
        String newUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new Customer(newUUID, createdAt, firstName, lastName, phone, email, city);
    }
}
