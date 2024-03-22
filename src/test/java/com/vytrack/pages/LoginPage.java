package com.vytrack.pages;

import static com.vytrack.utils.BrowserUtil.*;

import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.DataUtil;
import com.vytrack.utils.Driver;
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


    /**
     *
     * @param dataType positive, negative
     * @param as driver, storemanager, salesmanager
     */
    public void login(String dataType,String as){
        String username = getSingleString(as,"username",dataType);
        String password = DataUtil.decrypt(getSingleString(as,"password",dataType));
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

}
