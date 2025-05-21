package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

    @FindBy(xpath = "//*[text()='Sauce Labs Onesie']")
    public static WebElement SauceLabsOnesie;

    @FindBy(xpath = "//*[text()='Sauce Labs Bike Light']")
    public static WebElement SauceLabsBikeLight;

    @FindBy(xpath = "//*[text()='Sauce Labs Bolt T-Shirt']")
    public static WebElement SauceLabsBoltTShirt;

    @FindBy(xpath = "//*[text()='Sauce Labs Fleece Jacket']")
    public static WebElement SauceLabsFleeceJacket;

    @FindBy(xpath = "//*[text()='Sauce Labs Backpack']")
    public static WebElement SauceLabsBackpack;

    @FindBy(xpath = "//*[text()='Test.allTheThings() T-Shirt (Red)']")
    public static WebElement TestAllTheThingsTShirtRed;

    @FindBy(id = "back-to-products")
    public static WebElement backToProducts;
    
    @FindBy(className = "product_sort_container")
    public static WebElement sortBy;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div/div/div[2]/div[1]")
    public static WebElement productDescription;
}
