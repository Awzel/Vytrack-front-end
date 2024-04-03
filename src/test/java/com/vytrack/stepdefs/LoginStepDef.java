package com.vytrack.stepdefs;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.vytrack.enums.Titles;
import com.vytrack.pages.ForgotPasswordPage;
import com.vytrack.pages.LoginPage;
import static com.vytrack.utils.BrowserUtil.*;

import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
        String keyword = getTitle(title);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"Title: ".concat(keyword));
        TitleVerification(keyword);

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

    private String getTitle(String keyWord){
        switch (keyWord){
            case "Vehicles":
                keyWord = vehiclesPage.getTitle();
                break;
            case "Odometer":
                keyWord = Titles.ODOMETER.getValue();
                break;
            case "Dashboard":
                keyWord = "Dashboard";
        }
        return keyWord;
    }
}
