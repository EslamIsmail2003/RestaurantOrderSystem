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

public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private static final CustomerRepo customerRepo = new CustomerRepo();
    private static final CustomerService customerService = new CustomerService();
    private static final MenuService menuService = new MenuService();
    private static final MenuItemRepo menuItemRepo = new MenuItemRepo();
    private static final OrderRepo orderRepo = new OrderRepo();
    private static final OrderItemRepo orderItemRepo = new OrderItemRepo();

    public void placeOrder() {
        String email = Utils.getValidatedEmail("Enter your email: ");
        List<Customer> customers = customerRepo.getCustomerByEmail(email);

        if (customers.isEmpty()) {
            logger.warn("No customer registered with email: {}", email);
            System.out.println("Sorry, this email is not registered.");
            System.out.println("Would you like to register? (Yes/No)");
            String attempt = Utils.getStringInput();
            if (attempt.equalsIgnoreCase("Yes")) {
                customerService.registerCustomer();
            } else {
                System.out.println("Thanks for using our restaurant!");
                System.exit(0);
            }
            return;
        }
        Customer customer = customers.get(0);
        String newOrderUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        double totalAmount = 0;
        Order order = new Order(newOrderUUID, createdAt, customer.getId(), "Pending", totalAmount);
        List<OrderItem> orderItems = new ArrayList<>();
        while (true) {
            System.out.println("\n--- Menu ---");
            menuService.getAllMenuItems();
            System.out.println("Enter item name to add (or type 'done' to confirm order): ");
            String input = Utils.getStringInput();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            System.out.println("How many would you like? ");
            int quantity = Utils.getNumberInput();
            List<MenuItem> menuItems = menuItemRepo.getAllMenuItems();
            MenuItem selectedItem = null;
            for (MenuItem item : menuItems) {
                if (item.getName().equalsIgnoreCase(input)) {
                    selectedItem = item;
                    break;
                }
            }
            if (selectedItem == null) {
                System.out.println("No item with that name was found, please try again.");
                logger.warn("Menu item not found: {}", input);
                continue;
            }
            double lineTotal = selectedItem.getPrice() * quantity;
            totalAmount += lineTotal;

            String orderItemUUID = UUID.randomUUID().toString();
            OrderItem orderItem = new OrderItem(
                    orderItemUUID,
                    createdAt,
                    order.getId(),
                    selectedItem.getId(),
                    quantity,
                    selectedItem.getPrice()
            );
            orderItems.add(orderItem);

            System.out.printf("Added %dx %s ($%.2f)%n", quantity, selectedItem.getName(), lineTotal);
        }
        if (orderItems.isEmpty()) {
            System.out.println("No items were added. Order cancelled.");
            return;
        }
        order.setTotalAmount(totalAmount);
        orderRepo.insertOrder(order);
        for (OrderItem item : orderItems) {
            orderItemRepo.insertOrderItem(item);
        }
        System.out.printf("%nOrder placed successfully! Total: $%.2f%n", totalAmount);
        logger.info("Order {} placed for customer {}", order.getId(), customer.getId());
    }
}



