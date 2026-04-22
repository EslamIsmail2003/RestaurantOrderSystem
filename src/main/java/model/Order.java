package model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.IllegalFormatException;

@Getter
@Setter
public class Order extends BaseEntity {
    private String customerId;
    private String status;
    private double totalAmount;

    public Order(String id, Timestamp createdAt, String customerId, String status, double totalAmount) {
        super(id, createdAt);
        this.customerId = customerId;
        this.status = status.substring(0,1).toUpperCase()+ status.substring(1).toLowerCase();
        this.totalAmount = totalAmount;
    }

    public void displayOrder() {
        System.out.println("┌─────────────────────────────");
        System.out.printf("%-5s %-20s%n", "Order Id:", getId());
        System.out.printf("%-5s %-20s%n", "Customer Id:", getCustomerId());
        System.out.printf("%-5s %-20s%n", "Status:", getStatus());
        System.out.printf("%-5s %-20s%n", "Total Amount:" ,"$"+ getTotalAmount());
        System.out.printf("%-5s %-20s%n", "Created At:", getCreatedAt());
        System.out.println("└─────────────────────────────");
    }
}
