package listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import static listeners.ScreenShotUtil.takeScreenShot;

import org.apache.logging.log4j.*;

public class Listener implements WebDriverListener {
    private static final Logger logger = LogManager.getLogger(Listener.class);

    @Override
    public void beforeClick(WebElement element) {
        logger.info("Clicking on element: {}", element);
        System.out.println("Clicking on element: " + element);
        String stepName = "Clicked on element: " + element;
        takeScreenShot(stepName);
        
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("Clicked on element: {}", element);
        System.out.println("Clicked on element: " + element);
        String stepName = "Clicked on element: " + element;
        takeScreenShot(stepName);
    }

    @Override
    public void beforeIsSelected(WebElement element) {
        logger.info("Checking if element is selected: {}", element);
        System.out.println("Checking if element is selected: " + element);
        String stepName = "Clicked on element: " + element;
        takeScreenShot(stepName);
    }

    @Override
    public void afterIsSelected(WebElement element, boolean selected) {
        logger.info("Element is selected: {} - {}", element, selected);
        System.out.println("Element is selected: " + element + " - " + selected);
        String stepName = "Clicked on element: " + element;
        takeScreenShot(stepName);
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("Navigating to URL: {}", url);
        System.out.println("Navigating to URL: " + url);
        String stepName = "Clicked on element: " + url;
        takeScreenShot(stepName);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        logger.info("Navigated to URL: {}", url);
        System.out.println("Navigated to URL: " + url);
        String stepName = "Clicked on element: " + url;
        takeScreenShot(stepName);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        String stepName = "SendKeys to element: " + element + " - Keys: " + String.join(", ", keysToSend); 
        logger.info("Sending keys to element: {} - Keys: {}", element, String.join(", ", keysToSend));
        System.out.println("Sending keys to element: " + element + " - Keys: " + String.join(", ", keysToSend));
        takeScreenShot(stepName);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        String stepName = "SendKeys to element: " + element;
        logger.info("Sent keys to element: {} - Keys: {}", element, String.join(", ", keysToSend));
        System.out.println("Sent keys to element: " + element + " - Keys: " + String.join(", ", keysToSend));
        takeScreenShot(stepName);
    }
}