package model;

import lombok.Getter;

import java.sql.Timestamp;


@Getter
public class MenuItem extends BaseEntity {
    private String name;
    private String category;
    private double price;

public MenuItem(String id, Timestamp createdAt, String name, String category, double price){
    super(id,createdAt);
    this.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
    this.category = category.substring(0,1).toUpperCase()+category.substring(1).toLowerCase();
    this.price = price;
}
public void displayMenuItem(){
    System.out.println("Item name: " + getName() + " Item Id: " + getId() + " Category: " + getCategory() + " Price: " + getPrice() + " Created At: " + getCreatedAt());
}
}
