package com.vytrack.stepdefs;

import com.vytrack.pages.LoginPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginStepDef {

    LoginPage loginPage = new LoginPage();

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.getDriver().get("https://qa.fleetapps.io/user/login");
        BrowserUtil.TitleVerification("Login");
    }
    @Given("the user logged in with {string} datatype as {string}")
    public void the_user_logged_in_with_datatype_as(String dataType, String userType) {
        loginPage.login(dataType,userType);
    }
    @Then("user is on {string} page")
    public void user_is_on_page(String page) {
        Driver.closeDriver();
    }

}
