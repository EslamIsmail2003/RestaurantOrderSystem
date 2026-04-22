package service;


import model.MenuItem;
import model.Order;
import model.OrderHeader;
import model.ReceiptItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.MenuItemRepo;
import repository.OrderRepo;
import utils.Utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private static final MenuItemRepo menuItemRepo = new MenuItemRepo();
    private static final OrderRepo orderRepo = new OrderRepo();

    public void addNewMenuItem() {
        String itemName = Utils.getValidInputs("Enter the item name: ", " Invalid item name format! ");
        String category = Utils.getValidInputs("Enter the category: ", " Invalid category format! ");
        double price = Utils.getDoubleInputs("Enter the price: ", " Invalid price format! ");
        MenuItem newMenuItem = addMenuItem(itemName, category, price);
        menuItemRepo.insertMenuItem(newMenuItem);
    }

    public void getAllMenuItems() {
        List<MenuItem> menuItemList = menuItemRepo.getAllMenuItems();
        for (MenuItem menuItem : menuItemList) {
            menuItem.displayMenuItem();
        }
    }

    public void getMenuItemByCategory() {
        String category = Utils.getValidInputs("Enter category name: ", " Invalid category format! ");
        List<MenuItem> menuItems = menuItemRepo.getMenuItemByCategory(category);
        if (menuItems.isEmpty()) {
            logger.warn("No menu items were found for this category! {}", category);
            System.out.println("No menu items were found for this category! ");
        }
        for (MenuItem menuItem : menuItems) {
            menuItem.displayMenuItem();
        }
    }

    public void displayCategories() {
        List<String> categories = menuItemRepo.getAllCategories();
        System.out.println("================================");
        System.out.println("       CATEGORIES       ");
        System.out.println("================================");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).substring(0, 1).toUpperCase() + categories.get(i).substring(1).toLowerCase());
        }
    }

    public void getOrderByStatus() {
        System.out.println("Enter the status of the order: ");
        String status = Utils.getStringInput();
        status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
        List<Order> orders = orderRepo.getOrderByStatus(status);
        if (orders.isEmpty()) {
            logger.warn("There are no orders with this status! {}", status);
            System.out.println("There are no orders with this status! ");
        }
        for (Order order : orders) {
            order.displayOrder();
        }
    }

    public void getOrderByCustomerId() {
        System.out.println("Enter the customer's ID: ");
        String customerId = Utils.getStringInput();
        List<Order> orders = orderRepo.getOrderByCustomerId(customerId);
        if (orders.isEmpty()) {
            logger.warn("No orders were found! {}", customerId);
            System.out.println("There are no orders for this customer id! ");
            return;
        }
        for (Order order : orders) {
            order.displayOrder();
        }
    }

    public void revenueByCategory() {
        HashMap<String, Double> itemMap = menuItemRepo.getTotalRevenueByCategory();
        for (Map.Entry<String, Double> entry : itemMap.entrySet()) {
            String category = entry.getKey();
            double total_revenue = entry.getValue();
            System.out.printf("%-20s $%.2f%n", category, total_revenue);
        }
    }

    public void totalRevenue() {
        double total_revenue = orderRepo.getTotalRevenue();
        System.out.printf("Total Revenue: $%.2f%n", total_revenue);
    }

    public void topSellingItems() {
        HashMap<String, Integer> mapList = orderRepo.getTopSellingItems();
        for (Map.Entry<String, Integer> entry : mapList.entrySet()) {
            String name = entry.getKey();
            int total_ordered = entry.getValue();
            System.out.printf("%-30s %d orders%n", name, total_ordered);
        }
    }


    public void orderCountByStatus() {
        HashMap<String, Integer> itemMap = orderRepo.getOrderCountByStatus();
        for (Map.Entry<String, Integer> entry : itemMap.entrySet()) {
            String status = entry.getKey();
            int totalCount = entry.getValue();
            System.out.printf("%-30s %d orders%n", status, totalCount);
        }
    }


    public void displayAllOrders() {
        List<Order> orders = orderRepo.getAllOrders();
        System.out.println("==================================");
        System.out.println("         ORDER DETAILS            ");
        System.out.println("==================================");
        for (int i =0; i<orders.size(); i++){
            System.out.println((i+1) + ". ");
            orders.get(i).displayOrder();
        }
    }

    public void updateOrderStatus() {
        System.out.println("Enter order id: ");
        String orderId = Utils.getStringInput();
        List<Order> orders = orderRepo.getOrderByOrderId(orderId);
        if (orders.isEmpty()) {
            logger.warn("No active orders were found! {}", orderId);
            System.out.println("There are no active orders for this id! ");
            return;
        }
        System.out.println("Set the order status: ");
        String status = Utils.getStringInput();
        orderRepo.updateOrderByStatus(orderId, status);
        System.out.println("Order status has updated successfully! ");
    }

    public void printReceipt() {
        System.out.println("Enter order ID: ");
        String orderId = Utils.getStringInput();
        OrderHeader orderHeader = orderRepo.getOrderHeader(orderId);
        if (orderHeader == null) {
            System.out.println("Order not found! ");
            return;
        }
        List<ReceiptItem> receiptItemList = orderRepo.getOrderLineItems(orderId);
        System.out.println("================================");
        System.out.println("       RESTAURANT RECEIPT       ");
        System.out.println("================================");
        System.out.printf("Customer: %s%n", orderHeader.getCustomerName());
        System.out.printf("Order ID: %s%n", orderHeader.getOrderId());
        System.out.printf("Status:   %s%n", orderHeader.getStatus());
        System.out.println("--------------------------------");
        for (ReceiptItem items : receiptItemList) {
            String name = items.getName();
            int quantity = items.getQuantity();
            double price = items.getPrice();
            System.out.printf("%dx %-25s $%.2f%n", quantity, name, price * quantity);
        }
        System.out.println("--------------------------------");
        System.out.printf("TOTAL: $%.2f%n", orderHeader.getTotalAmount());
        System.out.println("================================");
    }


    private static MenuItem addMenuItem(String name, String category, double price) {
        String newUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new MenuItem(newUUID, createdAt, name, category, price);
    }
}

