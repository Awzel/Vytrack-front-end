package com.vytrack.stepdefs;

import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FleetStepDef {
    VehiclesPage vehiclesPage = new VehiclesPage();
    DashboardPage dashboardPage = new DashboardPage();
    @When("the user clicks {string} on {string}")
    public void theUserClicksOn(String tab, String option) {
        dashboardPage.navigateTo(option,tab);
    }

    @When("the user sets view per page to {string}")
    public void theUserSetsViewPerPageTo(String pageNumber) {
        vehiclesPage.setPage(pageNumber);
        String actualPageNumber = vehiclesPage.globalData.getPageNum();
        Assert.assertEquals(pageNumber,actualPageNumber);
    }

    @Then("the user should see correct number of vehicles")
    public void theUserShouldSeeCorrectNumberOfVehicles() {
        String expected = vehiclesPage.globalData.getPageNum();
        String actual = vehiclesPage.displayedNumberOfItems(expected);
        Assert.assertEquals(expected,actual);
    }
}
