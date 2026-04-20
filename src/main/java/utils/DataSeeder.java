package utils;

import model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.MenuItemRepo;

import java.io.*;
import java.sql.Timestamp;
import java.util.UUID;

public class DataSeeder {
    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private static final MenuItemRepo menuItemRepo = new MenuItemRepo();

    public void seedMenuItems() {
        try {
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("menu_items.csv");
            if (inputStream==null){
                throw new RuntimeException("menu_items.csv not found! ");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null){
                logger.info("Seeding menu items from CSV...");
                String[] parts = line.split(",");
                String category = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2].replace("$", "").trim());
                String newUUID = UUID.randomUUID().toString();
                Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                MenuItem menuItem = new MenuItem(newUUID,createdAt,name,category,price);
                menuItemRepo.insertMenuItem(menuItem);
                logger.debug("Inserting: category={}, name={}, price={}", category, name, price);
                logger.info("Menu items seeded successfully! ");
            }
        }catch (IOException e){
            logger.error("Failed to read menu_items.csv", e);
            throw new RuntimeException("Failed to seed menu items",e);
        }

    }
}
