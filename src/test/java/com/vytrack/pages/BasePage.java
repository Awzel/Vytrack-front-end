package com.vytrack.pages;

import com.vytrack.utils.BrowserUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage {

    @FindBy(xpath = "//ul[@class='nav-multilevel main-menu']/li")
    protected List<WebElement> menuBarOptions;

    @FindBy(xpath = "//li[contains(@class,'align-menu-right')]//ul/li[contains(@class,'single')]")
    protected List<WebElement> menuBarOptionDropDown;

    public void selectOption(String optionName){
        for (WebElement menuBarOption : menuBarOptionDropDown) {
            if (menuBarOption.getText().equals(optionName)){
                BrowserUtil.waitUntilVisible(menuBarOption);
                BrowserUtil.click(menuBarOption);
            }
        }
    }

    public void navigateTo(String option,String tab){

        for (WebElement menuBarOption : menuBarOptions) {
            if (menuBarOption.getText().equals(option)){
                BrowserUtil.hoverOverTo(menuBarOption);
                break;
            }
        }
        selectOption(tab);
    }

    public abstract String getTitle();

}
