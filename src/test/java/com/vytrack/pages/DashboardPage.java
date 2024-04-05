package com.vytrack.pages;

import com.vytrack.enums.Titles;
import com.vytrack.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage{

    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @Override
    public String getTitle() {
        return Titles.DASHBOARD.getValue();
    }

    @FindBy(xpath = "//div[.='You do not have permission to perform this action.']")
    protected WebElement alertMessage;
    public String getActualErrorMessage(){
        return alertMessage.getText();
    }
}
