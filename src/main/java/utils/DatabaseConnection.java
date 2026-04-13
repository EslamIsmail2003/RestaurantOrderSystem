package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static Connection instance = null;
    public static Connection getConnection() {
        if (instance == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties");

                if (inputStream == null) {
                    throw new RuntimeException(" application.properties not found! ");
                }
                properties.load(inputStream);
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                instance = DriverManager.getConnection(url, user, password);
                logger.info("Connected to database! ");
            } catch (SQLException | IOException e) {
                logger.error("Connection to database failed!", e);
            }
        }
        return instance;
    }
}
