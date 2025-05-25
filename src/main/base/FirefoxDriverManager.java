package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-extensions");
        
        return new FirefoxDriver(options);
    }
}