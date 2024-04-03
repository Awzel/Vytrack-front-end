package com.vytrack.stepdefs;

import com.vytrack.pages.*;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;


public class FleetStepDef {
    VehiclesPage vehiclesPage;
    DashboardPage dashboardPage;
    GeneralCarInfoPage generalCarInfoPage;
    GlobalData globalData;
    AddEventPage addEventPage;

    public FleetStepDef(VehiclesPage vehiclesPage, DashboardPage dashboardPage, GeneralCarInfoPage generalCarInfoPage, GlobalData globalData, AddEventPage addEventPage) {
        this.vehiclesPage = vehiclesPage;
        this.dashboardPage = dashboardPage;
        this.generalCarInfoPage = generalCarInfoPage;
        this.globalData = globalData;
        this.addEventPage = addEventPage;
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

    @Then("user is able to click Add Event button")
    public void user_is_able_to_click_add_event_button() {
        generalCarInfoPage.clickCreateEvent();
    }
    @Then("user is able to fill the event info and save")
    public void user_is_able_to_fill_the_event_info_and_save() {
        addEventPage.eventInfoInputAndSave();
        BrowserUtil.sleep(2);
    }
    @Then("user is able to see the event created under Activity tab")
    public void user_is_able_to_see_the_event_created_under_activity_tab() {
        BrowserUtil.sleep(2);
        addEventPage.clickActivityTab();
        BrowserUtil.sleep(2);
        Map<String, String> actualEventInfo = addEventPage.getEventInfoFromActivityTab();
        Map<String, String> expectedEventInfo = globalData.getObject();
        System.out.println(actualEventInfo);
        System.out.println(expectedEventInfo);

//        Assert.assertEquals(actualEventInfo, expectedEventInfo);*/
    }

}
