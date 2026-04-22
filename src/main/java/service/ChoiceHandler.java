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
    private final Map<Integer, Runnable> orderMenuActions = new HashMap<>();
    private final Map<Integer, Runnable> analyticsActions = new HashMap<>();

    public ChoiceHandler() {
        MenuService menuService = new MenuService();
        StaffService staffService = new StaffService();
        CustomerService customerService = new CustomerService();
        initAdminMenu(menuService);
        initStaffMenu(staffService);
        initCustomerMenu(customerService);
        initOrderMenu(menuService);
        initAnalyticsMenu(menuService);
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
            if (choice == 5){
                System.out.println("Going back...");
                return;
        }
            Runnable actions = staffMenuActions.get(choice);
            if (actions != null) {
                actions.run();
            } else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runOrderMenu(){
        while (true){
            printOrderMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5){
                System.out.println("Going back...");
                return;
            }
            Runnable actions = orderMenuActions.get(choice);
            if (actions!= null){
                actions.run();
            }else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runAnalyticsMenu(){
        while (true){
            printAnalyticsMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5){
                System.out.println("Going back...");
                return;
            }
            Runnable actions = analyticsActions.get(choice);
            if (actions != null){
                actions.run();
            }else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input! Please enter a number between 1 and 5");
            }
        }
    }

    public void runAdminMenu() {
        while (true) {
            printAdminMenu();
            int choice = Utils.getNumberInput();
            if (choice == 5){
                System.out.println("Going back...");
                return;
            }
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
            if (choice == 5) {
                System.out.println("Going back...");
                return;
            }
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
            if (choice ==1 ){
                runAdminMenu();
            }
            else if (choice ==2){
                runOrderMenu();
            }
            else if (choice ==3 ){
                runAnalyticsMenu();
            }
            else if (choice ==4){
                runStaffMenu();
            }
            else if (choice ==5){
                System.out.println("Going back...");
                return;
            }
            else {
                logger.error("Invalid input! {}", choice);
                System.out.println("Invalid input");
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
        adminMenuActions.put(3, menuService::getMenuItemByCategory);
        adminMenuActions.put(4, menuService::displayCategories);
        adminMenuActions.put(5, () -> System.out.println("Going back..."));
    }

    public void initOrderMenu(MenuService menuService){
        orderMenuActions.put(1, menuService::displayAllOrders);
        orderMenuActions.put(2, menuService::getOrderByStatus);
        orderMenuActions.put(3, menuService::updateOrderStatus);
        orderMenuActions.put(4, menuService::printReceipt);
        orderMenuActions.put(5, () -> System.out.println("Going back..."));
    }

    public void initAnalyticsMenu(MenuService menuService){
        analyticsActions.put(1, menuService::revenueByCategory);
        analyticsActions.put(2, menuService::topSellingItems);
        analyticsActions.put(3, menuService::totalRevenue);
        analyticsActions.put(4,menuService::orderCountByStatus);
        analyticsActions.put(5, () -> System.out.println("Going back..."));
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
        System.out.println("3. Display by category");
        System.out.println("4. Display all categories");
        System.out.println("5. Back");
    }

    public void printOrderMenu(){
        System.out.println("1. Display all orders");
        System.out.println("2. Display by status");
        System.out.println("3. Update order status");
        System.out.println("4. Order receipt");
        System.out.println("5. Back");
    }

    public void printAnalyticsMenu(){
        System.out.println("1. Revenue by category");
        System.out.println("2. Top selling items");
        System.out.println("3. Total revenue");
        System.out.println("4. Order count by status");
        System.out.println("5. Back");
    }

    public void printAdminControl() {
        System.out.println("1. Menu Management");
        System.out.println("2. Order Management");
        System.out.println("3. Analytics");
        System.out.println("4. Staff Management");
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
        System.out.println("2. Admin portal");
        System.out.println("3. Exit");
    }
}
