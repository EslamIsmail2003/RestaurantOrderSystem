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
        this.customerName = customerName;
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.status = status;
        this.totalAmount = totalAmount;
    }
    public void displayOrderHeader(){
        System.out.println("Customer Name: " + customerName + " Order Id: " + orderId + " Created At: " + createdAt + " Status: " + status + " Total Amount: " + totalAmount);
    }
}
