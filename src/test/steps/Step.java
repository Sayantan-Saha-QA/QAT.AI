package steps;

import static stepdefs.StepDef.*;
import io.cucumber.java.en.*;
import org.testng.annotations.Test;

import commonutils.RetryAnalyzer;
import commonutils.RetryCount;


public class Step{

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(0)
    @Given("^user is on the Swag Labs login page$")
    public void getloginTitle(){   
        launchPageTitle(); 
    }
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(0)
    @When("^user enters valid credentials and clicks login button$")
    public void login() {
        loginMethod();
    }
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(0)
    @Then("^user should be redirected to the Swag Labs inventory page$")
    public void verifyProductPage() {
        verifyProduct();  
    }
}