package com.vytrack.stepdefs;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.vytrack.pages.ForgotPasswordPage;
import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;

import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import com.vytrack.utils.JsonReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.velocity.runtime.directive.contrib.For;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage;
    VehiclesPage vehiclesPage;
    ForgotPasswordPage forgotPasswordPage;

    GlobalData globalData;

    public LoginStepDef(LoginPage loginPage, VehiclesPage vehiclesPage, ForgotPasswordPage forgotPasswordPage, GlobalData globalData) {
        this.loginPage = loginPage;
        this.vehiclesPage = vehiclesPage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.globalData = globalData;
    }

    @Given("user logged in with {string} datatype as {string}")
    public void user_logged_in_with_datatype_as(String dataType, String userType) {
        loginPage.login(dataType,userType);
    }
    @Then("user is on {string} page")
    public void user_is_on_page(String title) {
        if (title.equals("Vehicles")){
            title = vehiclesPage.getTitle();
        }
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Title: ".concat(title));
            TitleVerification(title);
        }

    @Then("the user click on {string} button")
    public void theUserClickOnButton(String arg0) {
        loginPage.logOut();
    }

    @Then("the checkbox should be checked")
    public void theCheckboxShouldBeChecked() {
        Assert.assertTrue(loginPage.rememberMeChecked());
    }

    @When("user click on remember me button")
    public void userClickOnRememberMeButton() {
        loginPage.rememberMeClick();
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
        if (globalData.getScenarioName().startsWith("unable to login")){
            Assert.assertTrue(loginPage.errorMessageDisplayed());
        }else {
            Assert.assertTrue(forgotPasswordPage.displayMessage(type));
        }
    }
}
