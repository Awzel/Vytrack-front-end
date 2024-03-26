package com.vytrack.stepdefs;

import com.vytrack.pages.ForgotPasswordPage;
import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;

import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.JsonReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.velocity.runtime.directive.contrib.For;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();
    VehiclesPage vehiclesPage = new VehiclesPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    @Given("the user logged in with {string} datatype as {string}")
    public void the_user_logged_in_with_datatype_as(String dataType, String userType) {
        loginPage.login(dataType,userType);
    }
    @Then("user is on {string} page")
    public void user_is_on_page(String title) {
        if (title.equals("Vehicles")){
            title = vehiclesPage.getTitle();
        }
            TitleVerification(title);
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

    @When("user click on 'Forgot your password?' button")
    public void user_click_on_forgot_your_password_button() {
        loginPage.clickForgotPasswordLink();
    }

    @When("user enter {string} Username or Email")
    public void user_enter_username_or_email(String dataType) {
        forgotPasswordPage.enterUsernameAndSubmit(dataType);
    }


    @Then("user should get {string} message")
    public void userShouldGetMessage(String type) {
        Assert.assertTrue(forgotPasswordPage.displayMessage(type));
    }
}
