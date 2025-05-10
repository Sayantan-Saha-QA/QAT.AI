package stepmethods;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

import static base.DriverBase.dr;
import static base.ExtentReportUtil.*;
import static pages.LoginPage.*;
import static pages.ProductPage.*;
import static commonutils.Asserts.*;
import static commonutils.CommonUtils.*;
import static commonutils.Waits.*;


public class StepMethods{


    public static void launchPageTitle(){

        try{
            
            createAndGetTest("Login Page Test");
            
            waitTitle("Swag Labs");
            System.out.println("Page title is: " + dr.getTitle());
            softAssertEquals(dr.getTitle(), "Swag Labs");
        }
        catch(Exception e){
            logger(e);
        }
    }

    public static void loginMethod(){

        try{

            createAndGetTest("Login Page Test");
            //test random credentials can be entered and deleted
            username.sendKeys("abcd");
            password.sendKeys("1234");
            clearText(username);
            clearText(password);

            String USERNAME = getConfig("USERNAME");
            String PASSWORD = getConfig("PASSWORD");
        
            username.sendKeys(USERNAME);
            password.sendKeys(PASSWORD);
            loginButton.click();
        }
        catch(Exception e){
            logger(e);
        }

    }

    public static void verifyProduct(){

        try{

            createAndGetTest("Login Page Test");

            waitTitle("Swag Labs");
            softAssertEquals(dr.getTitle(), "Swag Labs");
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