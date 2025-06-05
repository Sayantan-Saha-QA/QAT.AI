package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    // Find the first product name element (static fields do not support List injection)
    //find by xpaths for product names

    @FindBy(xpath = "//*[@id=\"item_4_title_link\"]/div")
    public static WebElement swagLabsBackpack;

    @FindBy(xpath = "//*[@id=\"item_0_title_link\"]/div")
    public static WebElement sauceLabsBikeLight;

    @FindBy(xpath = "//*[@id=\"item_1_title_link\"]/div")
    public static WebElement sauceLabsBoltTShirt;

    @FindBy(xpath = "//*[@id=\"item_2_title_link\"]/div")
    public static WebElement sauceLabsFleeceJacket;

    @FindBy(xpath = "//*[@id=\"item_3_title_link\"]/div")
    public static WebElement sauceLabsOnesie;

    @FindBy(xpath = "//*[@id=\"item_5_title_link\"]/div")
    public static WebElement testAllTheThingsTShirt;

    @FindBy(id = "back-to-products")
    public static WebElement backToProducts;
    
    @FindBy(className = "product_sort_container")
    public static WebElement sortBy;

    @FindBy(xpath = "//div[contains(@class,'inventory_item_desc')]")
    public static WebElement productDescription;

}