package com.vytrack.pages;

import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class AddEventPage {

    GlobalData globalData;

    public AddEventPage(GlobalData globalData) {
        this.globalData = globalData;
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='oro_calendar_event_form[title]']")
    protected WebElement titleInput;

    @FindBy(xpath = "//html//body[@id='tinymce']")
    protected WebElement descriptionInput;

    @FindBy(xpath = "//iframe")
    protected WebElement descriptionInputIframe;


    @FindBy(xpath = "//input[@name='oro_calendar_event_form[organizerDisplayName]']")
    protected WebElement organizerDisplayNameInput;

    @FindBy(xpath = "//input[@type='email']")
    protected WebElement organizerEmailInput;

    @FindBy(xpath = "//button[@type='submit']")
    protected WebElement saveBtn;

    @FindBy(xpath = "//a[text()='Activity']")
    protected WebElement activityTab;

    @FindBy(xpath = "//div[@id='accordion-item420']//label")
    protected List<WebElement> activityInfoKeys;

    @FindBy(xpath = "//div[@id='accordion-item420']//label//following-sibling::div//div")
    protected List<WebElement> activityInfoValues;


    public void eventInfoInputAndSave(){

        Map<String, String> eventData = new LinkedHashMap<>();
        eventData.put("title", "This is a new event");
        eventData.put("description", "Exciting new event coming up!");

        globalData.setObject(eventData);
        titleInput.sendKeys(globalData.getObject().get("title"));
        Driver.getDriver().switchTo().frame(descriptionInputIframe);
        descriptionInput.sendKeys(globalData.getObject().get("description"));
        Driver.getDriver().switchTo().parentFrame();
        BrowserUtil.sleep(2);
        BrowserUtil.click(saveBtn);

    }

    public void clickActivityTab(){
        BrowserUtil.click(activityTab);
    }

    public Map<String, String> getEventInfoFromActivityTab() {


        Map<String, String> eventInfo = new LinkedHashMap<>();

        List<String> actualEventInfoKeys = new ArrayList<>();
        for (WebElement each : activityInfoKeys) {
            actualEventInfoKeys.add(each.getText());
        }

        List<String> actualEventInfoValues = new ArrayList<>();
        for (WebElement each : activityInfoValues) {
            actualEventInfoValues.add(each.getText());
        }

        System.out.println(actualEventInfoKeys);
        System.out.println(actualEventInfoValues);

        for (int i = 0; i < actualEventInfoKeys.size(); i++) {
            eventInfo.put(actualEventInfoKeys.get(i), actualEventInfoValues.get(i));
        }

        return eventInfo;
    }

}
