package com.vytrack.stepdefs;

import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.VehiclesPage;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FleetStepDef {
    VehiclesPage vehiclesPage;
    DashboardPage dashboardPage;
    GlobalData globalData;

    public FleetStepDef(VehiclesPage vehiclesPage, DashboardPage dashboardPage, GlobalData globalData) {
        this.vehiclesPage = vehiclesPage;
        this.dashboardPage = dashboardPage;
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

    @Then("user cannot create cars")
    public void user_cannot_create_cars() {
        vehiclesPage.verifyCannotClickCreateCarBtn();
    }

}
