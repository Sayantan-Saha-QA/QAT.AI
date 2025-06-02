package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ChromeDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        return new SafariDriver();
    }
}