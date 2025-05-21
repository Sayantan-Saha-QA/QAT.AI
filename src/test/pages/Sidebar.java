package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Sidebar {

    @FindBy(id = "react-burger-menu-btn")
    public static WebElement menu;

    @FindBy(id = "inventory_sidebar_link")
    public static WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    public static WebElement about;

    @FindBy(id = "logout_sidebar_link")
    public static WebElement logout;

    
}
