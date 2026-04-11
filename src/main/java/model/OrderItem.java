package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class OrderItem extends BaseEntity{
    private String menuItemId;
    private int quantity;
    private double price;

public OrderItem(String id, Timestamp createdAt, String menuItemId, int quantity, double price){
    super(id, createdAt);
    this.menuItemId = menuItemId;
    this.quantity = quantity;
    this.price = price;
}
public void displayOrderItem(){
    System.out.println("Order Id: " + getId() + " Menu Item Id: " + getMenuItemId() + " Quantity: " + getQuantity() + " Price: " + getPrice() + " Created At: " + getCreatedAt());
}
}
