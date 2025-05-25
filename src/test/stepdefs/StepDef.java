package stepdefs;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;

import static base.DriverBase.*;
import static reporting.ExtentReportUtil.*;
import static pages.LoginPage.*;
import static pages.ProductPage.*;
import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;
import static commonutils.Waits.*;

import static commonutils.DatabaseUtil.*;

import datamodels.LoginCredential;

import org.testng.annotations.Listeners;
import listeners.TestNGListener;

@Listeners(TestNGListener.class)
public class StepDef{

    
    public static void launchPageTitle(){

        try{
            createAndGetTest("Login Page Test");
            waitTitle("Swag Labs");
            System.out.println("Page title is: " + getDr().getTitle());
            softAssertEquals(getDr().getTitle(), "Swag Labs");
        }
        catch(Exception e){
            logger(e);
        }
    }
    
    public static void loginMethod(){

        try{
            createAndGetTest("Login Page Test");
            //test random credentials can be entered and deleted
            String[] userName = getConfig("data", "USERNAME").split(",");
            String[] passWord = getConfig("data", "PASSWORD").split(",");

            LoginCredential cred = new LoginCredential();

            for (String user : userName){
                cred.setUsername(user);
                for (String pass : passWord){
                    cred.setPassword(pass);
                    
                    username.sendKeys(cred.getUsername());
                    password.sendKeys(cred.getPassword());
                    loginButton.click();
                    snap(cred.getUsername()+cred.getPassword());
                    
                    if (!getDr().getCurrentUrl().contains("inventory.html")) {
                        clearText(username);
                        clearText(password);
                    }
                }
            }
        }
        catch(Exception e){
            logger(e);
        }
    }
    
    public static void verifyProduct(){

        try{
            createAndGetTest("Login Page Test");
            waitTitle("Swag Labs");
            softAssertEquals(getDr().getTitle(), "Swag Labs");
            Thread.sleep(3000);
            
            new Select(sortBy).selectByVisibleText("Price (high to low)");
            Thread.sleep(3000);

            WebElement[] productsToCheck = {
                SauceLabsBackpack,
                SauceLabsBikeLight,
                SauceLabsBoltTShirt,
                SauceLabsFleeceJacket,
                SauceLabsOnesie,
            };

            for ( WebElement product : productsToCheck) {
                scrollAction(product);
                product.click();
                String productName = product.getText();
                String productDescriptionText = productDescription.getText();

                insertProductName(productDescriptionText);
                saveProductNameToJson(timestamp, productDescriptionText);

                waitVisibility(backToProducts);
                softAssertEquals(productDescription.getText(), productName);

                backToProducts.click();
            }
            Thread.sleep(3000);
        }
        catch(Exception e){
            logger(e);
        }    
    }
    
}