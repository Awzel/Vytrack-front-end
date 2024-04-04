package com.vytrack.stepdefs;

import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.GeneralCarInfoPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class FleetStepDef {
    VehiclesPage vehiclesPage;
    DashboardPage dashboardPage;
    GeneralCarInfoPage generalCarInfoPage;
    GlobalData globalData;

    public FleetStepDef(VehiclesPage vehiclesPage, DashboardPage dashboardPage, GeneralCarInfoPage generalCarInfoPage, GlobalData globalData) {
        this.vehiclesPage = vehiclesPage;
        this.dashboardPage = dashboardPage;
        this.generalCarInfoPage = generalCarInfoPage;
        this.globalData = globalData;
    }

    @When("the user clicks {string} on {string}")
    public void theUserClicksOn(String tab, String option) {
        dashboardPage.navigateTo(option,tab);
    }

    @When("user sets view per page to {string}")
    public void userSetsViewPerPageTo(String pageNumber) {
        vehiclesPage.setPage(pageNumber);
        String actualPageNumber = globalData.getPageNum();
        Assert.assertEquals(pageNumber,actualPageNumber);
    }

    @Then("user should see correct number of vehicles")
    public void userShouldSeeCorrectNumberOfVehicles() {
        String expected = globalData.getPageNum();
        String actual = vehiclesPage.displayedNumberOfItems(expected);
        Assert.assertEquals(expected,actual);
    }

    @When("user click to reset button")
    public void userAbleToClickResetButton() {
          vehiclesPage.resetButtonClick();
          vehiclesPage.setPage(vehiclesPage.getDefaultVpp());
    }
    @Then("user cannot create cars")
    public void user_cannot_create_cars() {
        vehiclesPage.verifyCannotClickCreateCarBtn();
    }

    @When("user selects information in column {string}")
    public void userSelectsInformationInColumn(String index) {
        vehiclesPage.saveAndSelect(index);
    }

    @Then("user should get the correct information from the object")
    public void userShouldGetTheCorrectInformationFromTheObject() {
        Assert.assertTrue(generalCarInfoPage.isSameObject());
    }
    @Then("user should get{string} message")
    public void userShouldGetMessage(String arg0) {
        String expectedAlertMessage= "You do not have permission to perform this action.";
        String actualAlertMessage= dashboardPage.getActualErrorMessage();
        Assert.assertEquals(actualAlertMessage,expectedAlertMessage);

    }
}
