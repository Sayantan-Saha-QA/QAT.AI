package commonutils;


import org.apache.logging.log4j.*;

import java.util.Set;

import static pages.Sidebar.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.DriverBase;

public class CommonUtils extends DriverBase{

    public static final Logger logger = LogManager.getLogger(CommonUtils.class);

    public static String getConfig(String fileName, String key) {
        return DriverBase.getConfig(fileName, key);
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

    public static void doubleClick(WebElement element){
        try{
            new Actions(getDr())
            .doubleClick(element)
            .build()
            .perform();
        }
        catch(Exception e){
            logger(e);
        }
    }

    public static void dragnDrop(WebElement source, WebElement target){

        try{
            new Actions(getDr()).
            clickAndHold(source).
            moveToElement(target).
            release(target).
            build().perform();
        } catch(Exception e){
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

    public static void switchToWindowHandle(String windowHandle) {
        Set<String> allWindowHandles = getDr().getWindowHandles();
        if (allWindowHandles.contains(windowHandle)) {
            getDr().switchTo().window(windowHandle);
            logger.info("Switched to window handle: {}", windowHandle);
        } else {
            logger.error("Window handle not found: {}", windowHandle);
        }
    }
   
}
