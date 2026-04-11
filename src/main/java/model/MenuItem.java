package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class MenuItem extends BaseEntity {
    private String name;
    private String category;
    private double price;

public MenuItem(String id, Timestamp createdAt, String name, String category, double price){
    super(id,createdAt);
    this.name = name;
    this.category = category;
    this.price = price;
}
public void displayMenuItem(){
    System.out.println("Item name: " + getName() + " Item Id: " + getId() + " Category: " + getCategory() + " Price: " + getPrice() + " Created At: " + getCreatedAt());
}
}
