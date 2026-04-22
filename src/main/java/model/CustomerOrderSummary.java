package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderSummary {
    private String full_name;
    private int total_orders;
    private int total_items;
    private double total_revenue;
    private String status;


public CustomerOrderSummary(String full_name, int total_orders, int total_items, double total_revenue, String status){
    this.full_name = full_name;
    this.total_orders = total_orders;
    this.total_items = total_items;
    this.total_revenue= total_revenue;
    this.status = status;
}

public void displayCustomerOrderSummary(){
    System.out.println("Full name: " + full_name + " Total Orders: " + total_orders + " Total Items: " + total_items + " Total Revenue: " + total_revenue + " Status: " + status);
}
}
