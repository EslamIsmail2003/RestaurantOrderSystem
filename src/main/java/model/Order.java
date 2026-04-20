package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class Order extends BaseEntity {
    private String customerId;
    private String status;
    private double totalAmount;

public Order(String id, Timestamp createdAt,String customerId, String status, double totalAmount){
    super(id, createdAt);
    this.customerId = customerId;
    this.status = status;
    this.totalAmount = totalAmount;
}
public void displayOrder(){
    System.out.println("Order Id: " + getId() + " Customer Id: " + getCustomerId() + " Total Amount: " + getTotalAmount() + " Created At: " + getCreatedAt());
}
}
