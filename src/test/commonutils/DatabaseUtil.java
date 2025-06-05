package commonutils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.ZonedDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseUtil {

    private DatabaseUtil() {
        // Prevent instantiation
    }

    public static String DB_URL = "jdbc:mysql://localhost:3306/product_name";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "1234";
    private static final String JSON_FILE = "product_names.json";

    public static ZonedDateTime timeStamp = ZonedDateTime.now(java.time.ZoneId.of("UTC"));
    public static String timestamp = timeStamp.toString(); // Get the current timestamp in UTC
    // Get a list of product names from the database

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Insert product name into the database and save to JSON
    public static void insertProductName(String productName) {
        String query = "INSERT INTO product_names (timeStamp, product_name) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setObject(1, timeStamp);
            stmt.setString(2, productName);
            stmt.executeUpdate();

            System.out.println("Inserted product: " + productName + " into the database.");

            // Save to JSON file as well
            saveProductNameToJson(timeStamp.toString(), productName);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert product name into the database.", e);
        }
    }

    // Save product name and timestamp to a JSON file
    public static void saveProductNameToJson(String timestamp, String productName) {
        JSONArray productArray = new JSONArray();

        // Read existing data if file exists
        try {
            if (Files.exists(Paths.get(JSON_FILE))) {
                String content = new String(Files.readAllBytes(Paths.get(JSON_FILE)));
                if (!content.isEmpty()) {
                    productArray = new JSONArray(content);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add new entry
        JSONObject productObj = new JSONObject();
        productObj.put("timestamp", timestamp);
        productObj.put("product_name", productName);
        productArray.put(productObj);

        // Write back to file
        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(productArray.toString(2)); // Pretty print with 2-space indent
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}