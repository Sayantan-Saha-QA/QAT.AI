package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    // Find the first product name element (static fields do not support List injection)
    @FindBy(xpath = "//div[contains(@class,'inventory_item_name')]")
    public static WebElement productName;

    @FindBy(id = "back-to-products")
    public static WebElement backToProducts;
    
    @FindBy(className = "product_sort_container")
    public static WebElement sortBy;

    @FindBy(xpath = "//div[contains(@class,'inventory_item_desc')]")
    public static WebElement productDescription;
        
}