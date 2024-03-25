package com.vytrack.stepdefs;

import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();
    @Given("the user logged in with {string} datatype as {string}")
    public void the_user_logged_in_with_datatype_as(String dataType, String userType) {
        loginPage.login(dataType,userType);
    }
    @Then("user is on {string} page")
    public void user_is_on_page(String page) {
            TitleVerification(page);
        }

    @Then("user  click on  {string} button")
    public void userClickOnButton(String arg0) {
        loginPage.logOut();
    }

    @Then("then the checkbox should be checked")
    public void thenTheCheckboxShouldBeChecked() {
        Assert.assertTrue(loginPage.rememberMeChecked());
    }

    @When("user click on remember me button")
    public void userClickOnRememberMeButton() {
        loginPage.rememberMeClick();
    }
    @Then("users  should get error message")
    public void usersShouldGetErrorMessage() {
       Assert.assertTrue(loginPage.errorMessageDisplayed());
    }
}
