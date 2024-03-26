package com.vytrack.stepdefs;

import com.vytrack.pages.ForgotPasswordPage;
import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;

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
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
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

    @When("I click on 'Forgot your password?' button")
    public void i_click_on_forgot_your_password_button() {
        BrowserUtil.click(forgotPasswordPage.resetPasswordLink);
    }

    @When("I enter {string} Username or Email")
    public void i_enter_username_or_email(String dataType) {
        if (dataType.equalsIgnoreCase("positive")) {
            forgotPasswordPage.usernameOrEmailInput.sendKeys(JsonReader.getSingleString("driver","username","positive"));
        } else if (dataType.equalsIgnoreCase("negative")) {
            forgotPasswordPage.usernameOrEmailInput.sendKeys(JsonReader.getSingleString("driver","username","negative"));
        }
        BrowserUtil.click(forgotPasswordPage.requestButton);
    }


    @Then("I should get success message")
    public void i_should_get_success_message() {
        forgotPasswordPage.forgotPasswordSuccessMsg.isDisplayed();
    }

    @Then("I should get error message")
    public void i_should_get_error_message() {
        forgotPasswordPage.forgotPasswordFailedMsg.isDisplayed();
    }

}
