package commonutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZonedDateTime;

public class DatabaseUtil {

    public static String DB_URL = "jdbc:mysql://localhost:3306/product_name";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "1234";

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Insert product name into the database
    public static void insertProductName(String productName) {
        String query = "INSERT INTO product_names (timeStamp, product_name) VALUES (?, ?)";

        ZonedDateTime timeStamp = ZonedDateTime.now(java.time.ZoneId.of("UTC"));
                                  
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, timeStamp);
            preparedStatement.setString(2, productName);
            preparedStatement.executeUpdate();

            System.out.println("Inserted product: " + productName + " into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert product name into the database.", e);
        }
    }
}