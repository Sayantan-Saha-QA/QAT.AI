package steps;


import static stepdefs.StepDef.*;
import io.cucumber.java.en.*;


public class Step{

    @Given("^user is on the Swag Labs login page$")
    public void getloginTitle(){   
        launchPageTitle(); 
    }
    
    @When("^user enters valid credentials and clicks login button$")
    public void login() {
        loginMethod();
    }
    
    @Then("^user should be redirected to the Swag Labs inventory page$")
    public void verifyProductPage() {
        verifyProduct();  
    }
}