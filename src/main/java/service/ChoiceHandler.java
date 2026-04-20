package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class ChoiceHandler {
    private static final Logger logger = LoggerFactory.getLogger(ChoiceHandler.class);
    private final Map<Integer, Runnable> customerMenuActions = new HashMap<>();
    private final Map<Integer, Runnable> adminMenuActions = new HashMap<>();
    private final Map<Integer, Runnable> staffMenuActions = new HashMap<>();
    private final Map<Integer, Runnable> mainMenuActions = new HashMap<>();

    public ChoiceHandler() {
        MenuService menuService = new MenuService();
        StaffService staffService = new StaffService();
        CustomerService customerService = new CustomerService();
        initAdminMenu(menuService);
        initStaffMenu(staffService);
        initCustomerMenu(customerService);
        initMainMenu();
    }

    public void initMainMenu() {
        mainMenuActions.put(1, this::runCustomerMenu);
        mainMenuActions.put(2, this::runAdminControl);
        mainMenuActions.put(3, () -> {
            System.out.println("Thanks for using our restaurant app! have a great day.");
            System.exit(0);
        });
    }

    public void runStaffMenu() {
        while (true) {
            printStaffMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5) return;
            Runnable actions = staffMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runAdminMenu() {
        while (true) {
            printAdminMenu();
            int choice = Utils.getNumberInput();
            if (choice == 9) return;
            Runnable actions = adminMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 9");
            }
        }
    }

    public void runCustomerMenu() {
        while (true) {
            printCustomerMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5) return;
            Runnable actions = customerMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runAdminControl() {
        while (true) {
            printAdminControl();
            int choice = Utils.getNumberInput();
            if (choice == 1) {
                runAdminMenu();
            }
            if (choice == 2) {
                runStaffMenu();
            }
            if (choice == 3) {
                return;
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input please enter a number between 1 and 3");
            }
        }
    }

    public void runMainMenu() {
        while (true) {
            printMainMenuOptions();
            int choice = Utils.getNumberInput();
            if (choice == 3) {
                System.out.println("Thanks for using our restaurant system! have a great day!");
                System.exit(0);
            }
            Runnable actions = mainMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 3");
            }
        }
    }

    public void initCustomerMenu(CustomerService customerService) {
        customerMenuActions.put(1, customerService::registerCustomer);
        customerMenuActions.put(2, customerService::orderProcess);
        customerMenuActions.put(3, customerService::getOrderByEmail);
        customerMenuActions.put(4, customerService::cancelOrder);
        customerMenuActions.put(5, () -> System.out.println("Going back..."));
    }

    public void initAdminMenu(MenuService menuService) {
        adminMenuActions.put(1, menuService::addNewMenuItem);
        adminMenuActions.put(2, menuService::getAllMenuItems);
        adminMenuActions.put(3, menuService::displayAllOrders);
        adminMenuActions.put(4, menuService::getMenuItemByCategory);
        adminMenuActions.put(5, menuService::displayCategories);
        adminMenuActions.put(6, menuService::revenueByCategory);
        adminMenuActions.put(7, menuService::topSellingItems);
        adminMenuActions.put(8, menuService::getOrderByStatus);
        adminMenuActions.put(9, () -> System.out.println("Going back..."));
    }

    public void initStaffMenu(StaffService staffService) {
        staffMenuActions.put(1, staffService::addStaff);
        staffMenuActions.put(2, staffService::getAllStaff);
        staffMenuActions.put(3, staffService::getStaffByEmail);
        staffMenuActions.put(4, staffService::getStaffByRole);
        staffMenuActions.put(5, () -> System.out.println("Going back..."));
    }

    public void printCustomerMenu() {
        System.out.println("1. Register");
        System.out.println("2. Place an order");
        System.out.println("3. Display order by email");
        System.out.println("4. Cancel an order");
        System.out.println("5. Back");
    }

    public void printAdminMenu() {
        System.out.println("1. Add new menu item");
        System.out.println("2. Display all menu items");
        System.out.println("3. Display all orders");
        System.out.println("4. Display menu item by category");
        System.out.println("5. Display categories");
        System.out.println("6. Display revenue by category");
        System.out.println("7. Display top selling items");
        System.out.println("8. Display order by status");
        System.out.println("9. Back");
    }

    public void printAdminControl() {
        System.out.println("1. Orders and Items");
        System.out.println("2. Staff control");
        System.out.println("3. Back");
    }

    public void printStaffMenu() {
        System.out.println("1. Add staff");
        System.out.println("2. Display all staff");
        System.out.println("3. Display staff by email");
        System.out.println("4. Display staff by role");
        System.out.println("5. Back");
    }

    public void printMainMenuOptions() {
        System.out.println("1. Customer portal");
        System.out.println("2. Admin portal");
        System.out.println("3. Exit");
    }
}
