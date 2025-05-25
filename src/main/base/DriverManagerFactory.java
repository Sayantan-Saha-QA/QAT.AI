package base;

import java.util.HashMap;
import java.util.Map;

public class DriverManagerFactory {
    private static final Map<String, Class<? extends DriverManager>> registry = new HashMap<>();

    // Register a browser type and its manager class
    public static void register(String browser, Class<? extends DriverManager> managerClass) {
        registry.put(browser.toLowerCase(), managerClass);
    }

    // Get an instance of the appropriate DriverManager
    public static DriverManager getManager(String browser) {
        Class<? extends DriverManager> clazz = registry.get(browser.toLowerCase());
        if (clazz == null) {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate DriverManager for: " + browser, e);
        }
    }
}