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
        mainMenuActions.put(2, this::runAdminMenu);
        mainMenuActions.put(3, this::runStaffMenu);
        mainMenuActions.put(4, () -> {
            System.out.println("Thanks for using our restaurant app! have a great day.");
            System.exit(0);
        });
    }

    public void runAdminMenu() {
        while (true) {
            printAdminMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5) return;
            Runnable actions = adminMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runCustomerMenu() {
        while (true) {
            printCustomerMenu();
            int choice = Utils.getNumberInput();
            if (choice == 3) return;
            Runnable actions = customerMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 3");
            }
        }
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

    public void runMainMenu() {
        while (true) {
            printMainMenuOptions();
            int choice = Utils.getNumberInput();
            if (choice == 4) {
                System.out.println("Thanks for using our restaurant system! have a great day!");
                System.exit(0);
            }
            Runnable actions = mainMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 4");
            }
        }
    }

    public void initCustomerMenu(CustomerService customerService) {
        customerMenuActions.put(1, customerService::registerCustomer);
        customerMenuActions.put(2, customerService::orderProcess);
        customerMenuActions.put(3, () -> System.out.println("Going back..."));
    }

    public void initAdminMenu(MenuService menuService) {
        adminMenuActions.put(1, menuService::addNewMenuItem);
        adminMenuActions.put(2, menuService::getAllMenuItems);
        adminMenuActions.put(3, menuService::getMenuItemByCategory);
        adminMenuActions.put(4, menuService::displayCategories);
        adminMenuActions.put(5, () -> System.out.println("Going back..."));
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
        System.out.println("3. Back");
    }

    public void printAdminMenu() {
        System.out.println("1. Add new menu item");
        System.out.println("2. Display all menu items");
        System.out.println("3. Display menu item by category");
        System.out.println("4. Display categories");
        System.out.println("5. Back");
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
        System.out.println("2. Admin panel (Items/Orders)");
        System.out.println("3. Admin panel (Staff control)");
        System.out.println("4. Exit");
    }


}
