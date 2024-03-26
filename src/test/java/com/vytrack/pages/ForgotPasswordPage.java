package com.vytrack.pages;

import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.JsonReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {

    public ForgotPasswordPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="prependedInput")
    protected WebElement usernameOrEmailInput;

    @FindBy( xpath = "//button[@type='submit']")
    protected WebElement requestButton;

    @FindBy(xpath = "//div[@class='alert alert-warn']")
    protected WebElement forgotPasswordSuccessMsg;

    @FindBy(xpath = "//div[@class='alert alert-error']")
    protected WebElement forgotPasswordFailedMsg;

    public void enterUsernameAndSubmit(String dataType){
        String username = JsonReader.getSingleString("driver","username",dataType);
        BrowserUtil.send_key(usernameOrEmailInput,username);
        BrowserUtil.click(requestButton);
    }

    public boolean displayMessage(String type){
        if(type.equals("success")){
            return forgotPasswordSuccessMsg.isDisplayed();
        } else if (type.equals("error")) {
            return forgotPasswordFailedMsg.isDisplayed();
        }
        throw new RuntimeException("no such type. only success and error");
    }

}
