package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.DataUtil;
import com.vytrack.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.vytrack.utils.JsonReader.*;


public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="prependedInput")
    public WebElement userName;

    @FindBy(id="prependedInput2")
    public WebElement password;

    @FindBy(name = "_submit")
    public WebElement submitBtn;



    @FindBy(xpath = "//li[@id='user-menu']/a")
    public WebElement accountButton;

    @FindBy(xpath ="//ul[@role='menu']/li[4]")
    public WebElement logoutButton;

    @FindBy(xpath = "//input[@id='remember_me']")
    public WebElement remember_me_btn;

    @FindBy(xpath = "//div[.='Invalid user name or password.']")
    public WebElement errorMessage;



    /**
     *
     * @param dataType positive, negative
     * @param as driver, storemanager, salesmanager
     */
    public void login(String dataType,String as){
        String username = getSingleString(as,"username",dataType);
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"username: "+username);

        String password = DataUtil.getPassword(getSingleString(as,"password",dataType));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"password: "+password);

        fillUpInput("username",username);
        fillUpInput("password",password);
        click(submitBtn);
    }

    public void fillUpInput(String inputName,String key){
        if (inputName.equalsIgnoreCase("username")){
            send_key(userName,key);
        }else if (inputName.equalsIgnoreCase("password")){
            send_key(password,key);
        } else {
            throw new RuntimeException("Unexpected input(s)");
        }
    }

    public void logOut(){
        BrowserUtil.click(accountButton);
        BrowserUtil.click(logoutButton);
    }

    public void rememberMeClick(){
        click(remember_me_btn);
    }

    public boolean rememberMeChecked(){
        return BrowserUtil.isChecked(remember_me_btn);
    }

    public boolean errorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }

}
