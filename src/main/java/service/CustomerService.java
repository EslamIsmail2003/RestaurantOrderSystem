package service;

import model.Customer;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.CustomerRepo;
import repository.MenuItemRepo;
import repository.OrderItemRepo;
import repository.OrderRepo;
import utils.Utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private static final CustomerRepo customerRepo = new CustomerRepo();
    private static final OrderRepo orderRepo = new OrderRepo();
    private static final MenuItemRepo menuItemRepo = new MenuItemRepo();
    private static final MenuService menuService = new MenuService();
    private static final OrderItemRepo orderItemRepo = new OrderItemRepo();

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
    public void orderProcess() {
        String email = Utils.getValidatedEmail("Enter your email: ");
        List<Customer> customers = customerRepo.getCustomerByEmail(email);
        if (customers.isEmpty()) {
            logger.warn("This email is not registered! {}", email);
            System.out.println("Sorry, this email is not registered! ");
            System.out.println("Would you like to register ? (Yes/No)");
            String attempt = Utils.getStringInput();
            if (attempt.equalsIgnoreCase("Yes")) {
                registerCustomer();
            } else {
                System.out.println("Thanks for using our restaurant! ");
                System.exit(0);
            }
            return;
        }
        Customer customer = customers.get(0);
        String newOrderId = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        double totalAmount = 0;
        String status = "Pending";
        Order order = new Order(newOrderId, createdAt, customer.getId(), status, totalAmount);
        List<OrderItem> orderItems = new ArrayList<>();
        while (true) {
            System.out.println("\n -------- Categories --------");
            menuService.displayCategories();

            System.out.println("Enter category number(or 0 to checkout)");
            int categoryChoice = Utils.getNumberInput();
            if (categoryChoice == 0) break;
            List<String> categories = menuItemRepo.getAllCategories();
            if (categoryChoice < 1 || categoryChoice > categories.size()) {
                System.out.println("Invalid choice! Please try again: ");
                continue;
            }
            String selectedCategories = categories.get(categoryChoice - 1);
            System.out.println("\n --- " + selectedCategories + " ---");
            List<MenuItem> items = menuItemRepo.getMenuItemByCategory(selectedCategories);
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName() + " -$ " + items.get(i).getPrice());
            }
            System.out.println("Enter item number to select \n or 0 to go back:");
            int choice = Utils.getNumberInput();
            if (choice == 0) continue;
            if (choice < 1 || choice > items.size()) {
                System.out.println("Invalid choice! Please try again: ");
                continue;
            }
            MenuItem selectedItem = items.get(choice - 1);
            System.out.println("How many of " + selectedItem.getName() + " do you want: ");
            int quantity = Utils.getNumberInput();
            double total = selectedItem.getPrice() * quantity;
            totalAmount += total;
            String orderNewUUID = UUID.randomUUID().toString();
            OrderItem orderItem = new OrderItem(orderNewUUID, createdAt, order.getId(), selectedItem.getId(), quantity, selectedItem.getPrice());
            orderItems.add(orderItem);
            System.out.println("Added " + quantity + " " + selectedItem.getName() + " " + total);
        }
        if (orderItems.isEmpty()) {
            System.out.println("No orders were added! order cancelled. ");
        }
        order.setTotalAmount(totalAmount);
        orderRepo.insertOrder(order);
        for (OrderItem item : orderItems) {
            orderItemRepo.insertOrderItem(item);
        }
        System.out.println("Order placed successfully! Total: " + totalAmount + " -$");
        logger.info("Order {} placed for customer {}", order.getId(), customer.getId());

    }


    private static Customer addCustomer(String firstName, String lastName, String phone, String email, String city) {
        String newUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new Customer(newUUID, createdAt, firstName, lastName, phone, email, city);
    }
}
