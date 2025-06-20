package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        return new ChromeDriver();
    }
}