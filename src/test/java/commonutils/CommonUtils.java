package commonutils;


import org.apache.logging.log4j.*;
import java.io.File;
import java.io.FileInputStream;

import static pages.Sidebar.*;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.DriverBase;


public class CommonUtils extends DriverBase{

    public static final Logger logger = LogManager.getLogger(CommonUtils.class);

    public static String getConfig(String key) {
        try {
            FileInputStream fis = new FileInputStream(new File("src/config.properties"));
            Properties prop = new Properties();
            prop.load(fis);
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.error("Error reading config file: {}", e.getMessage(), e);
            return null;
        }
    }

    // Explicitly initialize WebDriver and navigate to the URL
    public static void startUp() {
        try {
            setUp(); // Initialize WebDriver

            String url = getConfig("URL");

            if (url != null && !url.isEmpty()) {
                getDr().get(url);
                logger.info("Navigated to URL: {}", url);
            } else {
                logger.error("URL is not defined in the properties file.");
            }
        } catch (Exception e) {
            logger.error("Exception occurred during setup: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to initialize WebDriver or navigate to URL.", e);
        }
    }

    // Explicitly quit WebDriver and clean up resources
    public static void tearDown() {
        
        //logout from the application
        try {
            menu.click();
            Thread.sleep(2000);
            logout.click();
        } catch (InterruptedException e) {
            logger.error("Thread sleep interrupted: {}", e.getMessage(), e);
        } finally{
            closeBrowser();
        }
    }

    public static void logger(Exception e){
        
        logger.error("Exception occurred: {}", e.getMessage(), e);
        logger.info("Driver closed");
    }

    public static void clearText(WebElement element){
        try{
            new Actions(getDr())
            .keyDown(element, Keys.COMMAND)
            .sendKeys("a").keyUp(element, Keys.COMMAND)
            .pause(100)
            .sendKeys(Keys.DELETE)
            .build()
            .perform();
        }
        catch(Exception e){
            logger(e);
        }
    }

    public static void rightClick(WebElement element){
        try{
            new Actions(getDr()).
            contextClick(element)
            .build().perform();
        }
        catch(Exception e){
            logger(e);
        }
    }

    public static void scrollAction(WebElement element) {
        try {
            // Scroll the element into view using JavascriptExecutor
            JavascriptExecutor js = (JavascriptExecutor) getDr();
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
    
            // Perform the action after scrolling
            new Actions(getDr())
                .moveToElement(element)
                .click()
                .build()
                .perform();
        } catch (Exception e) {
            logger.error("Error during scrollAction: {}", e.getMessage(), e);
        }
    }

    public static void zoom(WebElement element, int zoomLevel) {
        JavascriptExecutor js = (JavascriptExecutor) getDr();
        js.executeScript("arguments[0].style.transform = 'scale(' + arguments[1] + ')';", element, zoomLevel);    
    }

    public static void getWindowhandles(){
        //Store all window handles in a set
        Set<String> allWindowHandles = getDr().getWindowHandles();
        //Iterate through the set and print each window handle
        for (String windowHandle : allWindowHandles) {
            System.out.println("Window Handle: " + windowHandle);
        }
        //Switch to the new window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(getDr().getWindowHandle())) {
                getDr().switchTo().window(windowHandle);
                break;
            }
        }

    }
   
}
