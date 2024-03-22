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
      //  BrowserUtil.TitleVerification(page);
        System.out.println(Driver.getDriver().getTitle());}

        @Then("user  click on  {string} button under user's name is displayed in the top right corner AF")
        public void user_click_on_button_under_user_s_name_is_displayed_in_the_top_right_corner_af(String string) {
            BrowserUtil.click(loginPage.LogoutDropdown);
        }
        @Then("user back to {string} page AF")
        public void user_back_to_page_af(String string) {
            BrowserUtil.click(loginPage.logoutButton);

        }

      //  Driver.closeDriver();
    }


