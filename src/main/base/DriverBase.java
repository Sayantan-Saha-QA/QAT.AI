package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import listeners.Listener;

import org.apache.logging.log4j.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DriverBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverBase.class);

    public static WebDriver setUp() {
        try {
            String browser = getConfig("config", "browser");
            if (browser == null) {
                throw new IllegalArgumentException("No browser specified. Please set 'browser' in config.properties or as a system property.");
            }
            DriverManager driverManager = DriverManagerFactory.getManager(browser);
            WebDriver webDriver = driverManager.createDriver();

            Listener listener = new Listener();
            WebDriver decoratedDriver = new EventFiringDecorator<>(listener).decorate(webDriver);

            // Store the decorated driver in ThreadLocal variable
            driver.set(decoratedDriver);

            String url = getConfig("config","URL");
            // Delete cookies and maximize window, but Safari does not support window maximize
            webDriver.manage().deleteAllCookies();
            if (!browser.equalsIgnoreCase("safari")) {
                webDriver.manage().window().maximize();
            } else {
                logger.warn("Safari does not support window maximize operation.");
            }

            if (url != null && !url.isEmpty()) {
                webDriver.get(url);
                logger.info("Navigated to URL: {}", url);
            } else {
                logger.warn("No URL specified in config.properties.");
            }

            driver.set(decoratedDriver);
            logger.info("WebDriver initialized successfully.");
        } catch (Exception e) {
            logger.error("Exception occurred during WebDriver setup: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver.", e);
        }
        return driver.get();
    }

    public static WebDriver getDr() {
        return driver.get();
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
            driver.remove();
        }
    }

    public static String getConfig(String fileName, String key) {
        try {
            FileInputStream fis = new FileInputStream(new File("src/"+fileName+".properties"));
            Properties prop = new Properties();
            prop.load(fis);
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.error("Error reading config file: {}", e.getMessage(), e);
            return null;
        }
    }
}