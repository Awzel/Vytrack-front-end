package com.vytrack.pages;

import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEventPage {

    public AddEventPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "oro_calendar_event_form_title-uid-660b10ed29291")
    protected WebElement titleInput;

    public void titleInput (){
        titleInput.sendKeys("This is a new event");
    }


}
