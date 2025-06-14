package stepdefs;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

import static base.DriverBase.*;

import java.util.List;

import static pages.LoginPage.*;
import static pages.ProductPage.*;
import pages.ProductPage;
import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;
import static commonutils.Waits.*;
import static commonutils.DatabaseUtil.*;

import datamodels.LoginCredential;

import org.testng.annotations.Listeners;
import listeners.TestNGListener;

@Listeners(TestNGListener.class)
public class StepDef {

    public static void launchPageTitle() {
        try {
            waitTitle("Swag Labs");
            System.out.println("Page title is: " + getDr().getTitle());
            softAssertEquals(getDr().getTitle(), "Swag Labs");
        } catch (Exception e) {
            logger(e);
        }
    }

    public static void loginMethod() {
        try {
            // test random credentials can be entered and deleted
            String[] userName = getConfig("data", "USERNAME").split(",");
            String[] passWord = getConfig("data", "PASSWORD").split(",");

            LoginCredential cred = new LoginCredential();

            for (String user : userName) {
                cred.setUsername(user);
                for (String pass : passWord) {
                    cred.setPassword(pass);

                    username.sendKeys(cred.getUsername());
                    password.sendKeys(cred.getPassword());
                    loginButton.click();

                    if (!getDr().getCurrentUrl().contains("inventory.html")) {
                        clearText(username);
                        clearText(password);
                    }
                }
            }
        } catch (Exception e) {
            logger(e);
        }
    }

    public static void verifyProduct() {
        try {
            waitTitle("Swag Labs");
            softAssertEquals(getDr().getTitle(), "Swag Labs");
            Thread.sleep(3000);

            Select dropdown = new Select(sortBy);
            List<WebElement> options = dropdown.getOptions();
            for (WebElement option : options) {
                System.out.println(option.getText());
            }

            dropdown.selectByVisibleText("Price (high to low)");
            Thread.sleep(3000);

            
            /*WebElement products[] = {
                swagLabsBackpack,
                sauceLabsBikeLight,
                sauceLabsBoltTShirt,
                sauceLabsFleeceJacket,
                sauceLabsOnesie,
                testAllTheThingsTShirt
            };*/

            String[] productsText = {
                "swagLabsBackpack",
                "sauceLabsBikeLight",
                "sauceLabsBoltTShirt",
                "sauceLabsFleeceJacket",
                "sauceLabsOnesie",
                "testAllTheThingsTShirt"
            };

            for (String productText : productsText) {   
                
                WebElement product = (WebElement) ProductPage.class.
                getField(productText).get(null);     

                scrollAction(product);
                product.click();
                String productDescriptionText = productDescription.getText();

                saveProductNameToJson(timestamp, productDescriptionText);

                waitVisibility(backToProducts);
                backToProducts.click();
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            logger(e);
        }
    }
}