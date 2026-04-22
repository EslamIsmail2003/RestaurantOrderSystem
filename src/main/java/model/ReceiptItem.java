package model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptItem {
    private String name;
    private int quantity;
    private double price;

    public ReceiptItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void displayReceiptItem() {
        System.out.println("Name: " + name + " Quantity: " + quantity + " Price: " + price);
    }
}
