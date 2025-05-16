package base;

import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.*;

public class DriverBase {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverBase.class);

    public static ChromeDriverManager chromeDriverManager = null;
    public static FirefoxDriverManager firefoxDriverManager = null;

    public static WebDriver setUp() {
        try {
            // Ensure driver managers are initialized
            if (firefoxDriverManager == null) {
                firefoxDriverManager = new FirefoxDriverManager();
            }
            if (chromeDriverManager == null) {
                chromeDriverManager = new ChromeDriverManager();
            }

            WebDriver webDriver = null;
            FileInputStream fis = new FileInputStream("src/config.properties");
            Properties prop = new Properties();
            prop.load(fis);
            String browser = prop.getProperty("browser");
            String url = prop.getProperty("URL");

            if (browser != null && browser.equalsIgnoreCase("firefox")) {
                webDriver = firefoxDriverManager.createDriver();
            } else if (browser != null && browser.equalsIgnoreCase("chrome")) {
                webDriver = chromeDriverManager.createDriver();
            }

            if (webDriver == null) {
                logger.error("No valid browser specified. Please set the 'browser' system property to 'chrome' or 'firefox'.");
                throw new RuntimeException("No valid browser specified.");
            }

            webDriver.manage().deleteAllCookies();
            webDriver.manage().window().maximize();

            if (url != null && !url.isEmpty()) {
                webDriver.get(url);
                logger.info("Navigated to URL: {}", url);
            } else {
                logger.warn("No URL specified in config.properties.");
            }

            driver.set(webDriver);
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
}
