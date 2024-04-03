package com.vytrack.pages;

import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEventPage {

    public AddEventPage(){
        PageFactory.initElements(Driver.getDriver(),this);
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

    public void eventInfoInput(){
        titleInput.sendKeys("This is a new event");
        Driver.getDriver().switchTo().frame(descriptionInputIframe);
        descriptionInput.sendKeys("Exciting new event coming up!");
        Driver.getDriver().switchTo().parentFrame();
        organizerDisplayNameInput.sendKeys("Joe Rogan");
        organizerEmailInput.sendKeys("jrogan123@email.com");
        BrowserUtil.click(saveBtn);
    }


}
