package service;

import model.Order;
import model.OrderHeader;
import model.ReceiptItem;
import repository.OrderRepo;
import utils.Utils;

import java.util.List;

public class OrderService {
    private static final OrderRepo orderRepo = new OrderRepo();
    public void printReceipt(){
        System.out.println("Enter order ID: ");
        String orderId = Utils.getStringInput();
        OrderHeader orderHeader = orderRepo.getOrderHeader(orderId);
        if (orderHeader == null){
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
        for (ReceiptItem items : receiptItemList){
            String name = items.getName();
            int quantity = items.getQuantity();
            double price = items.getPrice();
            System.out.printf("%dx %-25s $%.2f%n", quantity, name, price * quantity);
        }
        System.out.println("--------------------------------");
        System.out.printf("TOTAL: $%.2f%n", orderHeader.getTotalAmount());
        System.out.println("================================");
    }
}
