package service;


import model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.MenuItemRepo;
import utils.Utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private static final MenuItemRepo menuItemRepo = new MenuItemRepo();


    public void addNewMenuItem() {
        String itemName = Utils.getValidInputs("Enter the item name: ", " Invalid item name format! ");
        String category = Utils.getValidInputs("Enter the category: ", " Invalid category format! ");
        double price = Utils.getDoubleInputs("Enter the price: ", " Invalid price format! ");
        MenuItem newMenuItem = addMenuItem(itemName, category, price);
        menuItemRepo.insertMenuItem(newMenuItem);
    }

    public void getAllMenuItems() {
        List<MenuItem> menuItemList = menuItemRepo.getAllMenuItems();
        for (MenuItem menuItem : menuItemList) {
            menuItem.displayMenuItem();
        }
    }

    public void getMenuItemByCategory() {
        String category = Utils.getValidInputs("Enter category name: ", " Invalid category format! ");
        List<MenuItem> menuItems = menuItemRepo.getMenuItemByCategory(category);
        if (menuItems.isEmpty()) {
            logger.warn("No menu items were found for this category! {}", category);
            System.out.println("No menu items were found for this category! ");
        }
        for (MenuItem menuItem : menuItems) {
            menuItem.displayMenuItem();
        }
    }

    private static MenuItem addMenuItem(String name, String category, double price) {
        String newUUID = UUID.randomUUID().toString();
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        return new MenuItem(newUUID, createdAt, name, category, price);
    }
}
