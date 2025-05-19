@Swag_Labs

@login_positive
Feature: Swag Labs Login

    Scenario: Swag Labs Login
        Given user is on the Swag Labs login page
        When user enters valid credentials and clicks login button
        Then user should be redirected to the Swag Labs inventory page
        