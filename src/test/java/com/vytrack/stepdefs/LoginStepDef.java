package com.vytrack.stepdefs;

import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;

import com.vytrack.utils.BrowserUtil;
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

    @When("I click on 'Forgot your password?' button")
    public void i_click_on_forgot_your_password_button() {
        loginPage.resetPasswordLink.click();
    }

    @When("I enter {string} Username or Email")
    public void i_enter_username_or_email(String dataType) {
        if (dataType.equalsIgnoreCase("positive")) {
            loginPage.userName.sendKeys("user180");
        } else if (dataType.equalsIgnoreCase("negative")) {
            loginPage.userName.sendKeys("abcdefg");
        }
        loginPage.requestButton.click();
    }


    @Then("I should get success message")
    public void i_should_get_success_message() {
        loginPage.forgotPasswordSuccessMsg.isDisplayed();
    }

    @Then("I should get error message")
    public void i_should_get_error_message() {
        loginPage.forgotPasswordFailedMsg.isDisplayed();
    }

}
