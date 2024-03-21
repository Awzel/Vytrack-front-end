package com.vytrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public abstract class BasePage {

    @FindBy(xpath = "//ul[@class='nav-multilevel main-menu']/li/a")
    public List<WebElement> menuBarOptions;



}
