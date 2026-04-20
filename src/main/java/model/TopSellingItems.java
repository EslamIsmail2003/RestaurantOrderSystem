package model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopSellingItems {
    private String itemName;
    private String category;
    private int totalOrdered;

    public TopSellingItems(String itemName, String category, int totalOrdered){
        this.itemName = itemName;
        this.category = category;
        this.totalOrdered = totalOrdered;
    }
    public void displayTopSellingItems(){
        System.out.println("Item name: " + itemName + " Category: " + category + " Total ordered: " + totalOrdered);
    }
}
