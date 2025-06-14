package steps;

import static reporting.ExtentReportUtil.*;
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
        createTest("user is on the Swag Labs login page");
        launchPageTitle(); 
    }
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(0)
    @When("^user enters valid credentials and clicks login button$")
    public void login() {
        createTest("user enters valid credentials and clicks login button");
        loginMethod();
    }
    
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @RetryCount(0)
    @Then("^user should be redirected to the Swag Labs inventory page$")
    public void verifyProductPage() {
        createTest("user should be redirected to the Swag Labs inventory page");
        verifyProduct();  
    }
}