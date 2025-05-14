package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import org.apache.logging.log4j.*;

public class DriverBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverBase.class);

    // Get the WebDriver instance for the current thread
    public static WebDriver getDr() {
        return driver.get();
    }

    // Set up the WebDriver
    public static WebDriver setUp() {
        try {
            
            String driverPath = "/Users/sayantansaha/Downloads/QAT.AI/src/main/java/resources/geckodriver";
            // Set the GeckoDriver path
            System.setProperty("webdriver.gecko.driver", driverPath);

            // Configure Firefox options
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-extensions");

            // Initialize the WebDriver and set it in ThreadLocal
            WebDriver webDriver = new FirefoxDriver(options);
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            webDriver.manage().deleteAllCookies();
            webDriver.manage().window().maximize();

            driver.set(webDriver); // Associate WebDriver with the current thread
            logger.info("WebDriver initialized successfully.");
        } catch (Exception e) {
            logger.error("Exception occurred during WebDriver setup: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver.", e);
        }

        return getDr();
    } 

    public static void closeBrowser() {
    try {
        WebDriver currentDriver = getDr();
        if (currentDriver != null) {
            currentDriver.quit();
            logger.info("WebDriver closed successfully.");
        } else {
            logger.warn("No WebDriver instance found for this thread.");
        }
    } catch (Exception e) {
        logger.error("Error while closing the WebDriver: {}", e.getMessage(), e);
    } finally {
        driver.remove(); // Always remove the ThreadLocal reference
    }
}
}