package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
public class OrderItem extends BaseEntity {
    private String orderId;
    private String menuItemId;
    private int quantity;
    private double price;

    public OrderItem(String id, Timestamp createdAt, String orderId, String menuItemId, int quantity, double price) {
        super(id, createdAt);
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.price = price;
    }
}