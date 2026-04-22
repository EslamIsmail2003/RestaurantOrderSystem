package model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class OrderHeader {
    private String customerName;
    private String orderId;
    private Timestamp createdAt;
    private String status;
    private double totalAmount;

    public OrderHeader(String customerName, String orderId, Timestamp createdAt, String status, double totalAmount){
        String[] parts = customerName.split(" ");
        String capitalized = parts[0].substring(0,1).toUpperCase() + parts[0].toLowerCase() + " " + parts[1].substring(0,1).toUpperCase() + parts[1].substring(1).toLowerCase();
        this.customerName = capitalized;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.status = status;
        this.totalAmount = totalAmount;
    }
    public void displayOrderHeader(){
        System.out.println("Customer Name: " + customerName + " Order Id: " + orderId + " Created At: " + createdAt + " Status: " + status + " Total Amount: " + totalAmount);
    }
}
